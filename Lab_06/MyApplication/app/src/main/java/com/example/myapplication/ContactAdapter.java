package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context context;
    private List<Contact> contactList;

    public ContactAdapter(Context context, List<Contact> contactList){
        this.context = context;
        this.contactList = contactList;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Contact contact = contactList.get(i);

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.activity_test, null);
        }

        TextView ID = (TextView)convertView.findViewById(R.id.id_textView);
        ID.setText(Integer.toString(contact.getID()));

        TextView LN = (TextView)convertView.findViewById(R.id.last_name_view);
        LN.setText(contact.getLastName());

        TextView FN = (TextView)convertView.findViewById(R.id.first_name_view);
        FN.setText(contact.getFirstName());

        TextView phone = (TextView)convertView.findViewById(R.id.phone_view);
        phone.setText(contact.getPhone());

        return convertView;
    }
}
