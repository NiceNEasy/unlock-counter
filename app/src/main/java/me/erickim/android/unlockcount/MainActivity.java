package me.erickim.android.unlockcount;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // comment test

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UnlockCounter UC = new UnlockCounter((TextView) findViewById(R.id.numberDisplay));


        // declare & initiz
        Button centerIcon = (Button) findViewById(R.id.bt_centerIcon); // Click count button
        Button previousDay = (Button) findViewById(R.id.bt_previous); // button to show previous day count
        Button nextDay = (Button) findViewById(R.id.bt_next); // button to show next day count

        //Button listen object
        ButtonListener bl = new ButtonListener(UC);

        // center button clicked
        centerIcon.setOnClickListener(bl);
        // number displayer display number when "cetnerIcon" is clicked


        // previous button clicked
        previousDay.setOnClickListener(bl);

        // next button clicked
        nextDay.setOnClickListener(bl);

    }
}
