package com.example.myempire.edit;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends Activity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Button finish = (Button) findViewById(R.id.about_page_btn);
        finish.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                finish();
                System.exit(0);
            }
        });
    }
}
