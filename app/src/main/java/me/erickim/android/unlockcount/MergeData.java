package me.erickim.android.unlockcount;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import db.DailyLog;
import db.TotalLog;

public class MergeData implements Runnable {
	private volatile DailyLog dl;
	private volatile TotalLog tl;


	private int mergeInterval = 3600000; // Every hour
	private Handler mergeHandler;

	public MergeData(DailyLog daily, TotalLog total) {
		this.dl = daily;
		this.tl = total;
	}

	public void run() {
		try {
			mergeDataToTotal(dl, tl);
		} catch (Exception e) {

		} finally {
			mergeHandler.postDelayed(this, mergeInterval);
		}
		
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
};