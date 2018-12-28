package com.example.framework.lista.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        Toast.makeText(context, "Toast instead a notification", Toast.LENGTH_LONG).show();


    }
}


