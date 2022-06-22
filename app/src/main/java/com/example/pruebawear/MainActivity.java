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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private ActivityMainBinding binding;
    private Button wBoton = null;
    private Intent intent;
    private Intent dialIntent;
    private PendingIntent pendingIntent;

    private NotificationCompat.Builder notification;
    private Notification updateNotif;

    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;

    String idChannel = "Mi Canal";
    public static final int idNotification = 001;

    private NotificationCompat.BigTextStyle bigTextStyle;
    String longText = "Without Bigstyle, only a single line of text would be visible"+
            "Any addtitional text would not appear directly in the notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wBoton = findViewById(R.id.wButton);

        intent = new Intent(MainActivity.this, MainActivity.class);

        nm = NotificationManagerCompat.from(MainActivity.this);

        wearableExtender = new NotificationCompat.WearableExtender();
        bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);

        wBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent para simular llamada con botón en notificación
                dialIntent = new Intent(MainActivity.this,DialActivityB.class);
                dialIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent DialPendingIntent = PendingIntent.getActivity(MainActivity.this,0,dialIntent,PendingIntent.FLAG_ONE_SHOT);
                //start(view);
                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name = "Notification";
                NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);

                nm.createNotificationChannel(notificationChannel);

                pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                int max_progress = 100;
                int current_progress = 25;
                notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Accion estandar")
                        .setContentText("Notificacion con acción estándar")
                        .setContentIntent(pendingIntent)
                        .addAction(com.google.android.gms.base.R.drawable.common_google_signin_btn_icon_dark, "Llamar", DialPendingIntent)
                        .setProgress(max_progress,current_progress,true);

                nm.notify(idNotification, notification.build());

                /*
                notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Mi notification")
                        .setContentText("Mi primera notificacion wear")
                        .extend(wearableExtender);
                nm.notify(idNotification, notification.build());
                /*Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateNotif = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Mi notification")
                                .setContentText("Mi segunda notificacion wear")
                                .build();
                        nm.notify(idNotification, updateNotif);
                    }
                },5000);

                List<Notification> pages = new ArrayList<Notification>();

                for (int i = 1; i > 3; i++){
                    Notification nt = new NotificationCompat.Builder(MainActivity.this, idChannel)
                            .setContentTitle("Pagina " + i)
                            .setContentText("Texto de la página").
                            build();
                    pages.add(nt);
                }
                NotificationCompat.WearableExtender extender = new NotificationCompat
                        .WearableExtender()
                        .addPages(pages);

                Notification notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notificacion multipagina")
                        .setContentText("Esta es la primera página")
                        .extend(extender)
                        .build();
                nm.notify(idNotification,notification);*/
            }
        });
    }

    /*
     * Handler handler = new Handler();
     *
     * */
  /*notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification Wear")
                        .setContentIntent(pendingIntent)
                        .extend(wearableExtender)
                        .setStyle(bigTextStyle)
                        .setVibrate(new long[]{100,200,300,400,500,400,500,400})
                        .setStyle(bigTextStyle);*/


           /* public void start(View view){
                Timer t = new Timer();
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        String name = "Notification 2";
                        NotificationChannel notificationChannel = new NotificationChannel(idChannel, name, importance);

                        nm.createNotificationChannel(notificationChannel);

                        pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);

                        notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Mi notification")
                                .setContentText("Mi segunda notificacion wear")
                                .extend(wearableExtender);
                        nm.notify(idNotification, notification.build());
                    }
                };

                t.schedule(tt, 5000);


            }*/
}