package com.rivendev.youdo.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.rivendev.youdo.Model.DTO.Task;
import com.rivendev.youdo.R;
import com.rivendev.youdo.View.Activity.MainActivity;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        Task task = (Task) intent.getSerializableExtra("Task");
        taskNotify(context, task);


    }

    private void taskNotify(Context context, Task task) {


        Intent goMain = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, task.getId(), goMain, 0);

        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationManager notif = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);


        Notification notify = new NotificationCompat.Builder
                (context.getApplicationContext())
                .setSound(alarmSound)
                .setVibrate(new long[]{300, 700, 1000})
                .setContentTitle("ALERTA DE TAREFA")
                .setContentIntent(pendingIntent)
                .setContentText(task.getName()).
                        setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(task.getDescription()))
                .setSmallIcon(R.drawable.ic_launcher_coral).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(task.getId(), notify);
    }

}


