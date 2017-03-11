package com.jvsl.androidcountryflags;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import javax.xml.datatype.Duration;

public class MainActivity extends AppCompatActivity {
    ImageView flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flag = (ImageView) findViewById(R.id.img_flag);

        final EditText countryCode = (EditText) findViewById(R.id.txt_flag) ;
        Button btnAddFlag = (Button) findViewById(R.id.btn_add_flag);

        btnAddFlag.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (countryCode.getText() != null && !countryCode.getText().equals("")){
                    int flagId = new Flag(MainActivity.this).getFlagByCountryCode(countryCode.getText().toString());

                    if (flagId > 0){
                        flag.setImageResource(flagId);
                    }else{
                        Toast.makeText(MainActivity.this, "Tip a valid country code", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    }

