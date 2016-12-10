package me.erickim.android.unlockcount;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import db.CountLog;

/**
 * Created by kimeric on 12/7/16.
 */

public class LockReceiver extends BroadcastReceiver {

    private CountLog cl;

    public LockReceiver(CountLog countLog) {
        this.cl = countLog;
    }

    private void addToDB() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) return;
        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            // Add data to db
            // Basically means user just unlocked the phone
            // For now we just add a log message.
            Log.d("LockReceiver", "ACTION_SCREEN_OFF");
        }
        // Check if the same row has ON, otherwise skip (probably first time)

        /*
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF); // on -> off
        context.registerReceiver(me.erickim.android.unlockcount.LockReceiver, filter);
        */
    }
}


