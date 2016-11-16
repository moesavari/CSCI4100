package com.example.assignment2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class AddProductActivity extends Activity {

    private String newName = null;
    private String newDesc = null;
    private float newPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_activity);

        final ProductDBHelper prodDB = new ProductDBHelper(this);

        Button save = (Button)findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductActivity.this, BrowseProductsActivity.class);
                EditText name = (EditText)findViewById(R.id.insert_name_edit);
                newName = name.getText().toString();

                EditText description = (EditText)findViewById(R.id.insert_desc_edit);
                newDesc = description.getText().toString();

                EditText price = (EditText)findViewById(R.id.insert_price_d);
                newPrice = Float.parseFloat(price.getText().toString());

                prodDB.addNewProduct(newName, newDesc, newPrice);

                finish();
                startActivity(intent);
            }
        });

        Button cancel = (Button)findViewById(R.id.cancel_btn);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddProductActivity.this, BrowseProductsActivity.class);
                EditText name = (EditText)findViewById(R.id.insert_name_edit);
                name.setText("");

                EditText description = (EditText)findViewById(R.id.insert_desc_edit);
                description.setText("");

                EditText price = (EditText)findViewById(R.id.insert_price_d);
                price.setText("");

                finish();
                startActivity(intent);
            }
        });
    }
}
