package com.example.exam;


import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LatLng newLatLng;
    private BikeDBHelper bikeDBHelper;
    private ArrayList<Bike> bikeArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        newLatLng = getIntent().getExtras().getParcelable("POSITION");

        bikeDBHelper = new BikeDBHelper(this);
        bikeArrayList = new ArrayList<>(bikeDBHelper.getAllBikes());
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng position = newLatLng;
        mMap.addMarker(new MarkerOptions().position(position).title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(position, 14f));

        for(int i = 0; i < bikeArrayList.size(); i++){
            Bike bike = bikeArrayList.get(i);
            LatLng coordinates = new LatLng(bike.getLatitude(), bike.getLongitude());
            MarkerOptions marker = new MarkerOptions();
            marker.position(coordinates);
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.addMarker(marker);
        }
    }
}
