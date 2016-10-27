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
        UnlockCounter UC = new UnlockCounter((TextView) findViewById(R.id.dayInfo));
        // declare & initiz
        Button centerIcon = (Button) findViewById(R.id.centerIcon);
        ButtonListener bl = new ButtonListener(UC);

        centerIcon.setOnClickListener(bl);
    }
}
