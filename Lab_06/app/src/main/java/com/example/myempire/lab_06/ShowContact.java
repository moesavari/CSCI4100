package com.example.myempire.lab_06;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ShowContact extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        Button addContact = (Button) findViewById(R.id.add_contact_btn);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowContact.this, AddContact.class);
                startActivity(intent);
            }
        });

        Button deleteContact = (Button) findViewById(R.id.delete_contact_btn);
        deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShowContact.this, DeleteContact.class);
                startActivity(intent);
            }
        });
    }

}
