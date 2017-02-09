package com.jvsl.androidcountryflags;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView flag = (ImageView) findViewById(R.id.flag);
        ImageView bigFlag = (ImageView) findViewById(R.id.big_flag);
        flag.setImageResource(new Flag().getFlagByCountryCode(this, "br"));
        bigFlag.setImageResource(new Flag().getFlagByCountryCode(this, "ar"));
    }
}
