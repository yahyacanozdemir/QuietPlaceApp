package com.yahyacanozdemir.sessizyer;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity3 extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener{


    String cityName ;
    String stateName ;
    String countryName ;
    float  adress3Long,adress3Lat ;

    private GoogleMap mMap;
    LocationManager locationManager ;
    LocationListener locationListener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnMapLongClickListener(this);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

                SharedPreferences sharedPreferences = MapsActivity3.this.getSharedPreferences("com.yahyacanozdemir.sessizyer",MODE_PRIVATE);
                boolean firstTimeCheck = sharedPreferences.getBoolean("NotFirstTime",false);
                if(firstTimeCheck==false){
                    LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));
                    sharedPreferences.edit().putBoolean("NotFirstTime",true).apply();
                }
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
        };

        if (Build.VERSION.SDK_INT>=23){
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},1);
            }else {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                mMap.clear();
                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                if (lastLocation != null) {
                    LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15));
                }
            }
        }else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15));
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length>0){
            if(requestCode==1){
                if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                    Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    if (lastLocation != null) {
                        LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation, 15));
                    }
                }
            }
        }
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        List<Address> addresses = null;
        Marker marker = null;
        double lat=0, lng=0;

        //daha önceden oluşturulmuş işaretciler temizleme
        mMap.clear();

        lat = latLng.latitude;
        lng = latLng.longitude;

        if (marker != null) {
            marker.remove();
        }

        //geocoder ile verilen enlem ve boylamdaki adresi bir array olarak döndürme
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            adress3Lat= (float) lat;
            adress3Long= (float) lng;
            addresses = geocoder.getFromLocation(lat, lng, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try{
        cityName = addresses.get(0).getAddressLine(0);
        stateName = addresses.get(0).getAddressLine(1);
        countryName = addresses.get(0).getAddressLine(2);

        //marker ekleme
        Toast.makeText(getApplicationContext(), cityName , Toast.LENGTH_SHORT).show();
        marker = mMap.addMarker(new MarkerOptions()
                .position(latLng)//enlem ve boylam
                .title("Your Chosed Adress")//isim
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.markercizimi))//local bir icon
                .snippet(cityName + " " + stateName + "  " + countryName));

        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(), "Connection Error! Please Try Again!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onBackPressed() {
        try {
            String CityName = cityName;
            String StateName = stateName;
            String CountryName = countryName;

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);

            intent.putExtra("userAdress3", CityName);
            intent.putExtra("userAdress3Lat",adress3Lat);
            intent.putExtra("userAdress3Long",adress3Long);
            startActivity(intent);

        }catch (Exception e){
        }
    }
}
