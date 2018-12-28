package com.example.framework.lista.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import com.example.framework.lista.R;
import com.example.framework.lista.View.Activity.MainActivity;

public class MyAlarmService extends Service {
    private static final int NOTIFICATION_ID = 1;
    private NotificationManager notificationManager;
    private PendingIntent pendingIntent;


    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @SuppressWarnings("static-access")
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Context context = this.getApplicationContext();
        notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Intent mIntent = new Intent(this, MainActivity.class);
        pendingIntent = PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setVibrate(new long[]{5, 5, 5, 5, 5});

        builder.setLights(Color.RED, 3, 3);
        builder.setContentTitle("Alert Scheduled");
        builder.setContentText("Test");
        builder.setStyle(new NotificationCompat.BigTextStyle().bigText("Teste teste teste teste teste teste teste teste teste teste teste teste teste"));
        builder.setSmallIcon(R.drawable.ic_launcher_coral);
        builder.setContentIntent(pendingIntent);
        builder.setDefaults(Notification.DEFAULT_SOUND)
                .setDefaults(Notification.DEFAULT_ALL).setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}