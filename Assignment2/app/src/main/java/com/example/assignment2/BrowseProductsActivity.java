package com.example.assignment2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowseProductsActivity extends AppCompatActivity {

    private String showName = null;
    private String showDescription = null;
    private float showPrice;
    private float getBTPrice;
    private String bitCoinURL = "https://blockchain.info/tobtc?currency=CAD&value=";
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);

        final ProductDBHelper prodDB = new ProductDBHelper(this);
        prodDB.getAllProducts();

        product = new Product(2, "one", "lalala", 255);
        showProduct(product);

        Button next = (Button)findViewById(R.id.next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BrowseProductsActivity.this, "Next!", Toast.LENGTH_SHORT).show();
            }
        });

        Button prev = (Button)findViewById(R.id.prev_btn);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(BrowseProductsActivity.this, "Previous!", Toast.LENGTH_SHORT).show();
            }
        });

        Button delete = (Button)findViewById(R.id.delete_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prodDB.deleteProduct(product.getProductID());
                Toast.makeText(BrowseProductsActivity.this, "Deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item:
                Intent intent = new Intent(BrowseProductsActivity.this, AddProductActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showProduct(Product product){

        showName = product.getName();
        showDescription = product.getDescription();
        showPrice = product.getPrice();

        TextView name_to_show = (TextView)findViewById(R.id.name_add);
        name_to_show.setText(showName);

        TextView desc_to_show = (TextView)findViewById(R.id.desc_add);
        desc_to_show.setText(showDescription);

        TextView price_to_show = (TextView)findViewById(R.id.price_d_add);
        String stringTofloat = Float.toString(showPrice);
        price_to_show.setText(stringTofloat);

        getBTPrice = convertToBitCoin(showPrice);

        TextView btc_to_show = (TextView)findViewById(R.id.price_bt_add);
        stringTofloat = Float.toString(getBTPrice);
        btc_to_show.setText(stringTofloat);
    }

    public float convertToBitCoin(float price){

        float BTCprice = 0;

//        bitCoinURL+=price;
//
//        try {
//            URL url = new URL(bitCoinURL);
//
//            BufferedReader buffer = new BufferedReader(new InputStreamReader(url.openStream()));
//            String str;
//
//            while((str = buffer.readLine()) != null){
//                BTCprice = Float.parseFloat(str);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return BTCprice;
    }
}
