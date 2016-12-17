package com.example.exam;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity extends AppCompatActivity implements DataDownloadedListener,
                                                                AdapterView.OnItemSelectedListener{
    private BikeDBHelper bikeDBHelper;
    private List<Bike> bikes;
    private Bike bike;
    private static final String URL = "http://feeds.bikesharetoronto.com/stations/stations.xml";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bikeDBHelper = new BikeDBHelper(this);
        bikes = bikeDBHelper.getAllBikes();

        DownloadDataTask task = new DownloadDataTask(this);
        task.execute(URL);

        final Spinner bikeSpinner = (Spinner)findViewById(R.id.bike_spinner);
        bikeSpinner.setOnItemSelectedListener(this);

        Button save = (Button)findViewById(R.id.save_btn);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bike = bikes.get(bikeSpinner.getSelectedItemPosition());
                EditText setName = (EditText)findViewById(R.id.name_edit);
                bike.setName(setName.getText().toString());
                bikeDBHelper.updateBike(bike);
            }
        });

        Button showMap = (Button)findViewById(R.id.show_btn);
        showMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                LatLng position = new LatLng(43.6563589, -79.3909977);
                intent.putExtra("POSITION", position);

                startActivity(intent);
            }
        });
    }

    public void dataDownloaded(List<Bike> bikes){
        bikeDBHelper = new BikeDBHelper(this);
        bikes = bikeDBHelper.getAllBikes();

        ArrayAdapter<Bike> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, bikes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner bikeSpinner = (Spinner)findViewById(R.id.bike_spinner);
        bikeSpinner.setAdapter(adapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        bike = bikes.get(position);

        EditText setBikeShareId = (EditText)findViewById(R.id.bike_share_id_edit);
        setBikeShareId.setText(String.valueOf(bike.getBikeShareId()));

        EditText setName = (EditText)findViewById(R.id.name_edit);
        setName.setText(bike.getName());

        EditText setLat = (EditText)findViewById(R.id.lat_edit);
        setLat.setText(String.valueOf(bike.getLatitude()));

        EditText setLong = (EditText)findViewById(R.id.long_edit);
        setLong.setText(String.valueOf(bike.getLongitude()));

        EditText setAddress = (EditText)findViewById(R.id.address_edit);
        setAddress.setText(bike.getAddress());

        EditText setNumBikes = (EditText)findViewById(R.id.num_bikes_edit);
        setNumBikes.setText(String.valueOf(bike.getNumBikes()));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    class DownloadDataTask extends AsyncTask<String, Void, List<Bike>> {

        private Exception exception = null;
        private DataDownloadedListener listener = null;

        public DownloadDataTask(DataDownloadedListener listener) { this.listener = listener; }

        @Override
        protected List<Bike> doInBackground(String... params) {

            bikeDBHelper.deleteAllBikes();

            try{
                URL url = new URL(params[0]);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(urlConnection.getInputStream());

                NodeList node = document.getElementsByTagName("station");
                for(int i = 0; i < node.getLength(); i++) {
                    Element element = (Element)node.item(i);

                    NodeList title = element.getElementsByTagName("id");
                    Element line = (Element)title.item(0);
                    int bikeID = Integer.parseInt(line.getTextContent());

                    title = element.getElementsByTagName("name");
                    line = (Element)title.item(0);
                    String name = line.getTextContent();

                    title = element.getElementsByTagName("lat");
                    line = (Element)title.item(0);
                    double lat = Double.parseDouble(line.getTextContent());

                    title = element.getElementsByTagName("long");
                    line = (Element)title.item(0);
                    double lng = Double.parseDouble(line.getTextContent());

                    title = element.getElementsByTagName("nbBikes");
                    line = (Element)title.item(0);
                    int numBikes = Integer.parseInt(line.getTextContent());

                    String address = null;
                    if(Geocoder.isPresent()){
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                        try {
                            List<Address> ls = geocoder.getFromLocation(lat, lng, 1);

                            for(Address addr: ls){
                                address = addr.getAddressLine(0);
                            }
                        } catch (IOException e){
                            e.printStackTrace();
                        }
                    }

                    bike = new Bike(bikeID, lat, lng, name, numBikes, address);
                    bikes.add(bike);
                }

            } catch (Exception e) {
                Log.i("BIKEDATA", "exception: " + e);
                this.exception = e;
            }

            for(int i = 0; i < bikes.size(); i++){
                int bikeID = bikes.get(i).getBikeShareId();
                double lat = bikes.get(i).getLatitude();
                double lng = bikes.get(i).getLongitude();
                String name = bikes.get(i).getName();
                int numBikes = bikes.get(i).getNumBikes();
                String address = bikes.get(i).getAddress();

                bikeDBHelper.createBike(bikeID, lat, lng, name, numBikes, address);
            }
            return bikes;
        }

        @Override
        protected void onPostExecute(List<Bike> bike) {
            if (this.exception != null) {
                this.exception.printStackTrace();
            } else {
                this.listener.dataDownloaded(bike);
            }
        }
    }
}

