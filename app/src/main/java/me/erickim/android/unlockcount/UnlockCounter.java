package me.erickim.android.unlockcount;


import android.widget.TextView;

/**
 * Created by kimeric on 10/27/16.
 */

public class UnlockCounter {

    //field
    TextView textView;

    //contructor
    public UnlockCounter(TextView textView){
        this.textView = textView;
    }


    // method : increment by 1
    public void increment(String val) {
        textView.setText(val);
    }
}
