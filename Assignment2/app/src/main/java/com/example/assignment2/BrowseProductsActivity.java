package com.example.assignment2;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class BrowseProductsActivity extends AppCompatActivity {

    private String bitCoinURL = "https://blockchain.info/tobtc?currency=CAD&value=";
    private int locator;
    private Button prev;
    private Button next;
    private Button delete;
    private ProductDBHelper prodDB;
    private ArrayList<Product> displayProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);

        prodDB = new ProductDBHelper(this);
        displayProducts = new ArrayList<>(prodDB.getAllProducts());
        locator = 0;

        Product product = new Product(0, "Avocado", "Ripe and Tasty!", 6);
        Product product1 = new Product(1, "Balogne", "Savoury meat!", 23);
        Product product2 = new Product(2, "Knives", "Sharpest of them all, used " +
                                                    "by Chef Ramsay himself!", 125);

        displayProducts.add(product);
        displayProducts.add(product1);
        displayProducts.add(product2);

        showProduct(displayProducts.get(locator));

        next = (Button)findViewById(R.id.next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            locator++;
            showProduct(displayProducts.get(locator));
            }
        });

        prev = (Button)findViewById(R.id.prev_btn);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            locator--;
            showProduct(displayProducts.get(locator));
            }
        });

        delete = (Button)findViewById(R.id.delete_btn);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            prodDB.deleteProduct(displayProducts.get(locator).getProductID());
            locator = 0;
            showProduct(displayProducts.get(locator));
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

        String showName = product.getName();
        String showDescription = product.getDescription();
        float showPrice = product.getPrice();

        TextView name_to_show = (TextView)findViewById(R.id.name_add);
        name_to_show.setText(showName);

        TextView desc_to_show = (TextView)findViewById(R.id.desc_add);
        desc_to_show.setText(showDescription);

        TextView price_to_show = (TextView)findViewById(R.id.price_d_add);
        String stringTofloat = Float.toString(showPrice);
        price_to_show.setText(stringTofloat);

        float getBTPrice = convertToBitCoin(showPrice);

        TextView btc_to_show = (TextView)findViewById(R.id.price_bt_add);
        stringTofloat = Float.toString(getBTPrice);
        btc_to_show.setText(stringTofloat);
    }

    public float convertToBitCoin(float price){

        float BTCprice = 0;
        bitCoinURL+=price;

        try {
            BTCprice = new downloadFile().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        bitCoinURL = "https://blockchain.info/tobtc?currency=CAD&value=";

        return BTCprice;

    }
    class downloadFile extends AsyncTask<String, Void, Float> {

        public Float doInBackground(String... params) {

            float price = 0;
            try {
                URL url = new URL(bitCoinURL);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                int result = conn.getResponseCode();
                if (result == HttpURLConnection.HTTP_OK) {
                    InputStream comeIn = conn.getInputStream();
                    BufferedReader buffRead = new BufferedReader(new InputStreamReader(comeIn));
                    String line;
                    while ((line = buffRead.readLine()) != null) {
                        price = Float.parseFloat(line);
                    }
                }
            } catch (MalformedURLException e) {
                Log.d("HTTPUrlConnectionTest", "Malformed URL Exception.");
            } catch (IOException e) {
                Log.d("HTTPUrlConnectionTest", "IO Exception.");
            }
            return price;
        }
    }
}
