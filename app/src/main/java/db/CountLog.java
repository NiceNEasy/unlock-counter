package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Date;

/**
 * Created by kimeric on 12/9/16.
 */

public class CountLog extends SQLiteOpenHelper {

    public static final String LOGTAG = "COUNTUNCLOCK";

    private static final String DATABASE_NAME = "countLog.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_COUNTLOG = "countLog"; // Change to date in MM/DD/YYYY format

    /* Table 1 : Count log */
    private static final String COLUMN_COUNTLOG_UID = "uID";
    public static final String COLUMN_COUNTLOG_UNLOCKTIME = "unlockTime";
    // private static final String COLUMN_COUNTLOG_LOCKTIME = "lockTime";


    public static final String TABLE_COUNTLOG_CREATE =
            "CREATE TABLE" + TABLE_COUNTLOG + " (" +
                    COLUMN_COUNTLOG_UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_COUNTLOG_UNLOCKTIME+ " DATETIME " +
     //               COLUMN_COUNTLOG_LOCKTIME + " DATETIME " +
                    ")";

    public CountLog(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public boolean insertTime(Date now) {
        return false;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_COUNTLOG_CREATE);
        Log.i(LOGTAG, "CountLog has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_COUNTLOG);
        onCreate(sqLiteDatabase);
    }
}
