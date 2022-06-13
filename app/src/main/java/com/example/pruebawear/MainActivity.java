package com.example.pruebawear;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pruebawear.databinding.ActivityMainBinding;

public class MainActivity extends Activity {

    private Button wButton=null;
    private ActivityMainBinding binding;
    private Intent intent;
    private PendingIntent pendingIntent;
    private Notification updateNotif;
    private NotificationCompat.Builder notification;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;

    String idChannel="Mi_Canal";
    int idNotification=001;
    private NotificationCompat.BigTextStyle bigTextStyle;
    String longText="Without BigStyle, only a single line of text would be visible"+
                    "Any additional text would not appear directly in the notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wButton=findViewById(R.id.wButton);

        intent=new Intent(MainActivity.this, MainActivity.class);
        nm=NotificationManagerCompat.from(MainActivity.this);
        wearableExtender=new NotificationCompat.WearableExtender();
        bigTextStyle=new NotificationCompat.BigTextStyle().bigText(longText);

        wButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int important = NotificationManager.IMPORTANCE_HIGH;
                String name="Notificación";
                NotificationChannel notificationChannel=new NotificationChannel(idChannel,name,important);

                nm.createNotificationChannel(notificationChannel);

                pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,0 );
                notification= new NotificationCompat.Builder(MainActivity.this,idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification wear")
                        .setContentText(longText)
                        .setContentIntent(pendingIntent)
                        .extend(wearableExtender)
                        .setVibrate(new long[]{100,200,300,400,500,400})
                        .setStyle(bigTextStyle);
                //notification.setOnlyAlertOnce(true);
                //notification.setOngoing(true);
                //notification.setWhen(System.currentTimeMillis());
                //notification.setContentText(longText);
                //Notification notification1=notification.build();
                nm.notify(idNotification,notification.build());

                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateNotif=new NotificationCompat.Builder(MainActivity.this,idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Notification wear")
                                .setContentText(longText)
                                .setContentIntent(pendingIntent)
                                .build();
                        nm.notify(idNotification,updateNotif);
                    }
                },5000);
            }
        });
    }
}