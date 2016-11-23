package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends Activity{

    private int ID = 0;
    private String firstName;
    private String lastName;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        EditText id_edit = (EditText)findViewById(R.id.id_edit);
        ID = Integer.parseInt(id_edit.getText().toString());
        Log.i("contactmanager_add", String.valueOf(ID));

        final EditText FN_edit = (EditText)findViewById(R.id.fname_edit);

        final EditText LN_edit = (EditText)findViewById(R.id.lname_edit);

        final EditText phone_edit = (EditText)findViewById(R.id.phone_edit);

        final Button addContact = (Button)findViewById(R.id.add_btn);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                //ID = Integer.parseInt(id_to_int);
                firstName = FN_edit.getText().toString();
                lastName = LN_edit.getText().toString();
                phone = phone_edit.getText().toString();


                intent.putExtra("ContactID", ID);
                intent.putExtra("FirstName", firstName);
                intent.putExtra("LastName", lastName);
                intent.putExtra("PhoneNumber", phone);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
