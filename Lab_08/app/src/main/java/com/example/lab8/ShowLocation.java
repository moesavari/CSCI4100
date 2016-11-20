package com.example.lab8;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShowLocation extends AppCompatActivity implements LocationListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_MEDIUM);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        criteria.setAltitudeRequired(false);
        criteria.setBearingAccuracy(0);
        criteria.setSpeedAccuracy(0);
        criteria.setCostAllowed(false);

        String recommended = locationManager.getBestProvider(criteria, true);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                                                PackageManager.PERMISSION_GRANTED &&
                                                ActivityCompat.checkSelfPermission(this,
                                                Manifest.permission.ACCESS_COARSE_LOCATION) !=
                                                        PackageManager.PERMISSION_GRANTED) {
            return;
        }

        Location location = locationManager.getLastKnownLocation(recommended);

        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        Geocoder geocoder;
        List<Address> addresses = null;

        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = addresses.get(0).getAddressLine(0);
        String address_two = addresses.get(0).getAddressLine(1);
        String city = addresses.get(0).getLocality();
        String adminArea = addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        String postalCode = addresses.get(0).getPostalCode();
        String phone = addresses.get(0).getPhone();
        String URL = addresses.get(0).getUrl();

        TextView address1 = (TextView)findViewById(R.id.address_one);
        address1.setText(address);

        TextView address2 = (TextView)findViewById(R.id.address_two);
        address2.setText(address_two);

        TextView locality =(TextView)findViewById(R.id.locality);
        locality.setText(city);

        TextView area = (TextView)findViewById(R.id.province);
        area.setText(adminArea);

        TextView country_text = (TextView)findViewById(R.id.country);
        country_text.setText(country);

        TextView postal_code = (TextView)findViewById(R.id.postal);
        postal_code.setText(postalCode);

        TextView phoneNumber = (TextView)findViewById(R.id.phone);
        phoneNumber.setText(phone);

        TextView url_show = (TextView)findViewById(R.id.url);
        url_show.setText(URL);
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
