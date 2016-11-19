package me.erickim.android.unlockcount;

import android.content.Intent;
import android.view.View;

import static android.R.attr.start;

/**
 * Created by kimeric on 10/27/16.
 */

public class ButtonListener implements View.OnClickListener {

    //field
    private UnlockCounter UC;

    //constructor
    public ButtonListener(UnlockCounter UC){
        this.UC = UC;
    }

    // count number button
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_centerIcon:
                UC.addCount();
                break;
            case R.id.bt_share:
                Intent myIntent = new Intent(Intent.ACTION_SEND);
                myIntent.setType("text/plain");
                String shareBody = "Your body here";
                String shareSub = "Your subject here";
                myIntent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
                myIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                startActivity(Intent.createChooser(myIntent, "Share using"));
                break;

            /*case R.id.bt_previous: // go to previous day button
                *//*TODO : show the previous day information*//*
                break;
            case R.id.bt_previous:  // go to next day button
                *//*TODO : show the next day information *//*
                break;*/

            default:
        }
    }






}
