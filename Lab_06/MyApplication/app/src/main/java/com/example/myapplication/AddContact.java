package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends Activity{

    static final int PICK_CONTACT_REQUEST = 1;
    private int ID = 0;
    private String firstName;
    private String lastName;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        EditText id_edit = (EditText)findViewById(R.id.id_edit);
        final String id_to_int = id_edit.getText().toString();

        final EditText FN_edit = (EditText)findViewById(R.id.fname_edit);

        final EditText LN_edit = (EditText)findViewById(R.id.lname_edit);

        final EditText phone_edit = (EditText)findViewById(R.id.phone_edit);

        final Button addContact = (Button)findViewById(R.id.add_btn);
        addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddContact.this, MainActivity.class);

                ID = parseToInt(id_to_int, 0);
                firstName = FN_edit.getText().toString();
                lastName = LN_edit.getText().toString();
                phone = phone_edit.getText().toString();

                intent.putExtra("contactID", ID);
                intent.putExtra("FirstName", firstName);
                intent.putExtra("LastName", lastName);
                intent.putExtra("PhoneNumber", phone);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }

    int parseToInt(String maybeInt, int defaultValue){
        if (maybeInt == null) return defaultValue;
        maybeInt = maybeInt.trim();
        if (maybeInt.isEmpty()) return defaultValue;
        return Integer.parseInt(maybeInt);
    }
}
