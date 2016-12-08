package me.erickim.android.unlockcount;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by kimeric on 12/7/16.
 */

public class LockReceiver extends BroadcastReceiver {

    public void insertDataToDaily(Intent intent) {
        // TODO: gets triggered when phone gets locked/unlocked
        intent.getAction().equals(Intent.ACTION_SCREEN_OFF);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) return;
        insertDataToDaily(intent);

        if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            // Add data to db
            // Basically means user just locked the phone
        }
        // Check if the same row has ON, otherwise skip (probably first time)

        /*
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF); // on -> off
        context.registerReceiver(me.erickim.android.unlockcount.LockReceiver, filter);
        */
    }
}


