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

    // method : add count by 1
    public void addCount() {
        String currentNumber = "0";
        currentNumber = textView.getText().toString();
        int result = Integer.parseInt(currentNumber);
        result += 1;
        String clickedNumber = Integer.toString(result);
        textView.setText(clickedNumber);
    }

    // method : share in facebook
//    public void share() {
//    }


}
