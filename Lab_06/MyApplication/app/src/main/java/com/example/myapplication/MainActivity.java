package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final int PICK_CONTACT_REQUEST = 1;

    private int ID = 0;
    private String firstName = null;
    private String lastName = null;
    private String phone = null;
    private List<Contact> contactList;
    private ContactAdapter contactAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.list_of_contacts);

        contactList = new ArrayList<>();

        contactList.add(new Contact(1, "help", "derop", "kekek"));
        contactList.add(new Contact(2, "tsfa", "asfag", "kekek"));
        contactList.add(new Contact(3, "help", "derop", "kekek"));
        contactList.add(new Contact(4, "help", "derop", "kekek"));

        final Intent intent = new Intent(this, AddContact.class);

        contactAdapter = new ContactAdapter(this, contactList);
        listView.setAdapter(contactAdapter);

        Button add = (Button)findViewById(R.id.add_contact_btn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivityForResult(intent, PICK_CONTACT_REQUEST);
            }
        });

        Button delete = (Button)findViewById(R.id.delete_contact_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DeleteContact.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){

        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == PICK_CONTACT_REQUEST){
            if(resultCode == RESULT_OK){

                ID = intent.getExtras().getInt("ContactID");
                firstName = intent.getStringExtra("FirstName");
                lastName = intent.getStringExtra("LastName");
                phone = intent.getStringExtra("PhoneNumber");

                Log.d("contactmanager", String.valueOf(ID));
                Log.d("contactmanager", firstName);

                contactList.add(new Contact(ID, firstName, lastName, phone));
                contactAdapter = new ContactAdapter(this, contactList);
                listView.setAdapter(contactAdapter);
            }
        }
    }
}
