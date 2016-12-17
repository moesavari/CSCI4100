package com.example.lab09;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener,
                                                GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    final int PERMISSIONS_REQUEST_ACCESS_LOCATION = 410020;
    private GoogleMap mMap;
    private LocationRequest locationRequest;
    private GoogleApiClient googleApiClient;
    private LatLng coordinates;
    private Marker currentLocationMarker;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setupLocationServices();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestLocationPermissions() {
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_LOCATION);
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setupLocationServices() {
        requestLocationPermissions();
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            String locationConfig = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            Intent enableGPS = new Intent(locationConfig);
            startActivity(enableGPS);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        googleApiClient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null && !googleApiClient.isConnected())
            googleApiClient.connect();
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        buildGoogleApiClient();
        googleApiClient.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        String address = null;
        String address_two = null;
        String city = null;
        String adminArea = null;
        String country = null;
        String postalCode = null;
        String phone = null;
        String URL = null;

        if (currentLocationMarker != null) {
            currentLocationMarker.remove();
        }

        if(Geocoder.isPresent()){
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            try {
                List<Address> listOfAddress = geocoder.getFromLocation(lat, lng, 1);
                for (android.location.Address addr : listOfAddress) {
                    address = addr.getAddressLine(0);
                    address_two = addr.getAddressLine(1);
                    city = addr.getLocality();
                    adminArea = addr.getAdminArea();
                    country = addr.getCountryName();
                    postalCode = addr.getPostalCode();
                    phone = addr.getPhone();
                    URL = addr.getUrl();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

            String completeAddress = address + " " + address_two + " " + city + " " + adminArea + " " +
                    country + " " + postalCode + " " + phone + " " + URL;

        coordinates = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(coordinates);
        markerOptions.title(completeAddress);
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
        currentLocationMarker = mMap.addMarker(markerOptions);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(coordinates).zoom(14).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location lastLocation = null;
        String address = null;
        String address_two = null;
        String city = null;
        String adminArea = null;
        String country = null;
        String postalCode = null;
        String phone = null;
        String URL = null;

        //checks for permission to use fine location
        if (checkSelfPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        }


        //if the last location is not null, the coordinates will get its lat/lng attributes and set on map
        if (lastLocation != null) {
            if(Geocoder.isPresent()){
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                double lat = lastLocation.getLatitude();
                double lng = lastLocation.getLongitude();

                try {
                    List<Address> listOfAddress = geocoder.getFromLocation(lat, lng, 1);
                    for(android.location.Address addr: listOfAddress) {
                        address = addr.getAddressLine(0);
                        address_two = addr.getAddressLine(1);
                        city = addr.getLocality();
                        adminArea = addr.getAdminArea();
                        country = addr.getCountryName();
                        postalCode = addr.getPostalCode();
                        phone = addr.getPhone();
                        URL = addr.getUrl();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            String completeAddress = address + " " + address_two + " " + city + " " + adminArea + " " +
                                        country + " " + postalCode + " " + phone + " " + URL;
            coordinates = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(coordinates);
            markerOptions.title(completeAddress);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA));
            currentLocationMarker = mMap.addMarker(markerOptions);
        }

        //Creating a new location request to set current location
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
