package me.erickim.android.unlockcount;

import android.view.View;

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
            /*case R.id.bt_previous: // go to previous day button
                *//*TODO : Move to the previous day view*//*
                break;
            case R.id.bt_previous:  // go to next day button
                *//*TODO : Move to the next day view*//*
                break;*/
            default:
        }
    }






}
