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
import android.widget.Toast;

import db.DailyLog;
import db.TotalLog;

import static android.icu.text.MessagePattern.ArgType.SELECT;
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

    public void mergeDataToTotal(DailyLog daily, TotalLog total) {
        // TODO: concludes data on Daily to Total
        SQLiteDatabase db = daily.getReadableDatabase();

        // SQL Query to get tablename(dailylog) from database (Dailylog)
        Cursor c = db.rawQuery("SELECT name FROM dailyLog.db WHERE type='table'", null);
        String date = c.getString(0);

        // SQL Query to count how many row
        Cursor mCount = db.rawQuery("SELECT COUNT(*) FROM table_name", null);
        int count = mCount.getInt(0);

        // SQL Query to get each duration of each session

        // SQL Query to calculate total duration



    }

    Runnable mergeData = new Runnable() {
        @Override
        public void run() {
            try {
                mergeDataToTotal();
            } catch (Exception e) {
                // Possible exceptions:
                // 1. User is online though midnight
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
