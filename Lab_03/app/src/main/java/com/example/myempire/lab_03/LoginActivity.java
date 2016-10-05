package com.example.myempire.lab_03;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button login = (Button) findViewById(R.id.login_btn);
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view)
            {
                EditText user = (EditText)findViewById(R.id.user_enter);
                EditText pass = (EditText)findViewById(R.id.pass_enter);

                if(user.getText().toString().equals("msavari") && pass.getText().toString().equals("100604449"))
                {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
