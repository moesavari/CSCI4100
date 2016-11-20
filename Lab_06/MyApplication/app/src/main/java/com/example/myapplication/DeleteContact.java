package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class DeleteContact extends Activity {

    ArrayList<Integer> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        contactList = new ArrayList<>();

        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, contactList);

        final Spinner contact_spinner = (Spinner)findViewById(R.id.delete_spinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        contact_spinner.setAdapter(adapter);

        Button delete = (Button)findViewById(R.id.delete_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeleteContact.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
