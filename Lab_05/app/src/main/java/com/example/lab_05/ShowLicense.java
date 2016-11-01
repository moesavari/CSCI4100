package com.example.lab_05;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class ShowLicense extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_license);
        String value = "";

        TextView license = (TextView) findViewById(R.id.content_to_show_txt);
        license.setMovementMethod(new ScrollingMovementMethod());

        try {
            value = new DownloadLicense().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        license.setText(value);

        Button close = (Button) findViewById(R.id.close_BTN);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });
    }
}
