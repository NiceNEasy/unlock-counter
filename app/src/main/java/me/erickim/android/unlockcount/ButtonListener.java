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

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.centerIcon:
                UC.increment("1");
                break;
            default:
        }
    }
}
