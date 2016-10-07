package com.example.myempire.edit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.login_page_btn);
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
                    Toast.makeText(LoginActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
