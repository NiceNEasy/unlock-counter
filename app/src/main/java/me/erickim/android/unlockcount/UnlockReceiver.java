package me.erickim.android.unlockcount;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import db.CountLog;

/**
 * Created by kimeric on 12/7/16.
 */

public class UnlockReceiver extends BroadcastReceiver {


    private CountLog cl;

    public UnlockReceiver(CountLog countLog) {
        this.cl = countLog;
    }

    public UnlockReceiver() {

    }

    public long countRows() {
        SQLiteDatabase db = cl.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM countLog", null);
        return c.getCount();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent == null) return;
        // insertDataToDaily(intent);
        if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            // Add data to db
            // Basically means user just unlocked the phone
            // For now we just add a log message
            Log.d("UnlockReceiver", "ACTION_USER_PRESENT");

        }
    }

    public boolean updateToDB() {
        // Cursor mRows = db.rawQuery("SELECT COUNT(*) FROM countLog", null);
        // int rows = mRows.getInt(0);
        // Date[] dates_on = new Date[rows];
        // Date[] dates_off = new Date[rows];

        // SQL Query to get each duration of each session
        // Cursor sessionDurOnTime = db.rawQuery("SELECT unlockTime FROM countLog", null);
        // Cursor sessionDurOffTime = db.rawQuery("SELECT lockTime FROM countLog", null);

        // obtain time, date in current timezone based on location
        // GregorianCalendar calendar = new GregorianCalendar();
        //dateFormat.setTimeZone(TimeZone.getTimeZone("Amercia/New_York"));
        /* TODO : instead of time of new york, need to get location information */

        SQLiteDatabase db = cl.getWritableDatabase();

        Date now = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss aa");

        String str = dateFormat.format(now);
        ContentValues values = new ContentValues();
        values.put(cl.COLUMN_COUNTLOG_UNLOCKTIME, str);
        long id = db.insert(cl.TABLE_COUNTLOG, null, values);
        if (id == -1) {
            return false;
        }
        return true;
    }


}