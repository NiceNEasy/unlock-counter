package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kimeric on 11/25/16.
 */

public class DailyLog extends SQLiteOpenHelper {

    public static final String LOGTAG = "COUNTUNCLOCK";

    private static final String DATABASE_NAME = "dailyLog.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_DAILYLOG = "dailyLog"; // Change to date in MM/DD/YYYY format
    private static final String TABLE_TOTALLOG = "totalLog";

    /* Table 1 : Daily log */
    private static final String COLUMN_DAILYLOG_UID = "uID";
    private static final String COLUMN_DAILYLOG_ONTIME = "onTime";
    private static final String COLUMN_DAILYLOG_OFFTIME = "offTime";


    public static final String TABLE_DAILYLOG_CREATE =
            "CREATE TABLE" + TABLE_DAILYLOG + " (" +
                    COLUMN_DAILYLOG_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_DAILYLOG_ONTIME + " DATETIME, " +
                    COLUMN_DAILYLOG_OFFTIME + " DATETIME " +
                    ")";

    public DailyLog(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_DAILYLOG_CREATE);
        Log.i(LOGTAG, "daily log has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_DAILYLOG);
        onCreate(sqLiteDatabase);
    }
}
