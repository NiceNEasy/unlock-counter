package me.erickim.android.unlockcount;


import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.text.format.Time;

import java.lang.Object.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


import db.DailyLog;
import db.TotalLog;

import static android.R.attr.duration;
import static android.R.attr.start;
import static me.erickim.android.unlockcount.R.id.count;


public class MainActivity extends AppCompatActivity {

    // comment test

    private int mergeInterval = 3600000; // Every hour
    private Handler mergeHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnlockCounter UC = new UnlockCounter((TextView) findViewById(R.id.display_number));
        UnlockCounter DisplayDate = new UnlockCounter((TextView) findViewById(R.id.display_date));
        UnlockCounter DisplayTimeUsed = new UnlockCounter((TextView) findViewById(R.id.display_usedTime));

        // declare & initiz
        Button centerIcon = (Button) findViewById(R.id.bt_centerIcon);  // button to count number
        Button previousDay = (Button) findViewById(R.id.bt_previous);   // button to show previous day count
        Button nextDay = (Button) findViewById(R.id.bt_next);           // button to show next day count
        Button share = (Button) findViewById(R.id.bt_share);            // button to share in facebook

        
        //Button listener object
        ButtonListener bl = new ButtonListener(UC);

        // center button clicked
        centerIcon.setOnClickListener(bl);
        // number displayer display number when "centerIcon" is clicked

        // previous button clicked
        previousDay.setOnClickListener(bl);

        // next button clicked
        nextDay.setOnClickListener(bl);

        // share button clicked
        share.setOnClickListener(bl);

        DailyLog dailyLog = new DailyLog(null); //TODO: fix parameter
        TotalLog totalLog = new TotalLog(null); //TODO: fix parameter

    }

    // move to stat activity
    public void movetToStatisticActivity (View v){
        Intent i = new Intent(MainActivity.this, Statistic.class);
        startActivity(i);
    }

    // move to friends activity
    public void moveToFriendsActivity (View v){
        Intent j = new Intent(MainActivity.this, Friends.class);
        startActivity(j);
    }

    // move to account activity
    public void moveToAccountActivity (View v){
        Intent k = new Intent(MainActivity.this, Account.class);
        startActivity(k);
    }

    public void insertDataToDaily() {
        // TODO: gets triggered when phone gets locked/unlocked
    }

    public void mergeDataToTotal(DailyLog daily, TotalLog total) throws InvalidSessionException {
        // TODO: concludes data on Daily to Total
        SQLiteDatabase db = daily.getReadableDatabase();

        // SQL Query to count number(totallog) of unlock session
        Cursor mRows = db.rawQuery("SELECT COUNT(*) FROM totalLog", null);
        int rows = mRows.getInt(0);
        Date[] dates_on = new Date[rows];
        Date[] dates_off = new Date[rows];

        // SQL Query to get each duration of each session
        Cursor sessionDurOnTime = db.rawQuery("SELECT onTime FROM dailyLog", null);
        Cursor sessionDurOffTime = db.rawQuery("SELECT offTime FROM dailyLog", null);

        // obtain time, date in current timezone based on location
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd aa HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("Amercia/New_York"));
        /* TODO : instead of time of new york, need to get location information */
        String str = dateFormat.format(calendar.getTime());

        for (int i = 0; i < rows; i++) {
            String onTime = sessionDurOnTime.getString(i);
            String offTime = sessionDurOffTime.getString(i);
            Date date_on  = null;
            Date date_off = null;
            if (onTime != null) {
                try {
                    date_on = dateFormat.parse(onTime);
                    dates_on[i] = date_on;
                } catch (ParseException e) {
                    date_on = null;
                }
            }

            if (offTime != null) {
                try {
                    date_off = dateFormat.parse(offTime);
                    dates_off[i] = date_off;
                } catch (ParseException e) {
                    date_off = null;
                }
            } else {
                dates_on[i] = null;
                throw new InvalidSessionException();
            }

            // Calculate single duration
            if (date_on == null || date_off == null){
                return;
            }
            long difference = date_off.getTime() - date_on.getTime();
            System.out.println(difference/1000);
        }





        // SQL Query to calculate total duration per day -> sum of each sessions


    }

    Runnable mergeData = new Runnable() {
        @Override
        public void run() {
            try {
                mergeDataToTotal();
            } catch (Exception e) {
                // Possible exceptions:
                // 1. User is online though midnight -> start logging in the next dailylog
                // 2. Day has not concluded -->
            } finally {
                mergeHandler.postDelayed(mergeData, mergeInterval);
            }
        }
    };

    void startMerge() {
        mergeData.run();
    }

    void stopMerge() {
        mergeHandler.removeCallbacks(mergeData);
    }
}
