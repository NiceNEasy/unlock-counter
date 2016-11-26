package db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by kimeric on 11/25/16.
 */

public class TotalLog extends SQLiteOpenHelper {

    public static final String LOGTAG = "COUNTUNCLOCK";

    private static final String DATABASE_NAME = "totalLog.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_TOTALLOG = "totalLog";

    /* Table 2 : Total Log */
    private static final String COLUMN_TOTALLOG_ID = "ID";
    private static final String COLUMN_TOTALLOG_COUNT = "count";
    private static final String COLUMN_TOTALLOG_DATE = "date";
    private static final String COLUMN_TOTALLOG_USAGETIME = "usageTime";

    public static final String TABLE_TOTALLOG_CREATE =
            "CREATE TABLE " + TABLE_TOTALLOG + " (" +
                    COLUMN_TOTALLOG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TOTALLOG_DATE + " DATE, " +
                    COLUMN_TOTALLOG_COUNT + "INTEGER, " +
                    COLUMN_TOTALLOG_USAGETIME + " STRING " +
                    ")";

    public TotalLog(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_TOTALLOG_CREATE);
        Log.i(LOGTAG, "total log has been created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + TABLE_TOTALLOG);
        onCreate(sqLiteDatabase);
    }

}
