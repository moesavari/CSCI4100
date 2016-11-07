package com.example.myempire.lab_06;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends Activity{

    private EditText id_edit;
    private EditText fname;
    private EditText lname;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add contact");
        setContentView(R.layout.activity_add_contact);

        id_edit = (EditText) findViewById(R.id.id_edit);
        fname = (EditText) findViewById(R.id.firstName_edit);
        lname = (EditText) findViewById(R.id.lastName_edit);
        phone = (EditText) findViewById(R.id.firstName_edit);



        Button add_contact = (Button) findViewById(R.id.add_btn);
        add_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContact.this, ShowContact.class);

                String ID = id_edit.getText().toString();
                String firstName = fname.getText().toString();
                String lastName = lname.getText().toString();
                String phoneNumber = phone.getText().toString();

                intent.putExtra("ID", ID);
                intent.putExtra("First_Name", firstName);
                intent.putExtra("Last_Name", lastName);
                intent.putExtra("Phone", phoneNumber);
                startActivity(intent);
            }
        });

    }
}
