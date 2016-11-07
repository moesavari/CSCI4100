package com.example.myempire.lab_06;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowContact extends Activity{

    private ListView list;
    List<Contact> contacts;
    ArrayAdapter<Contact> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);

        Intent intent = new Intent();
        String ID = intent.getStringExtra("ID");
        String fName = intent.getStringExtra("First_Name");
        String lName = intent.getStringExtra("Last_Name");
        String phone = intent.getStringExtra("Phone");

        System.out.println("Test" + ID + fName + lName + phone);

        contacts = new ArrayList<Contact>();

        Contact contact = new Contact(ID, fName, lName, phone);
        contacts.add(contact);

        adapter = new ArrayAdapter<Contact>(this, android.R.layout.simple_list_item_1, contacts);
        list = (ListView) findViewById(R.id.list_of_contacts);
        list.setAdapter(adapter);

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
