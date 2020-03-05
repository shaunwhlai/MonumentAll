package com.example.monumentall;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

// Base Stitch Packages
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.mongodb.client.FindIterable;
import com.mongodb.stitch.android.core.Stitch;
import com.mongodb.stitch.android.core.StitchAppClient;

// Packages needed to interact with MongoDB and Stitch
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

// Necessary component for working with MongoDB Mobile
import com.mongodb.stitch.android.core.auth.StitchUser;
import com.mongodb.stitch.android.services.mongodb.local.LocalMongoDbService;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient;
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection;
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential;

import org.w3c.dom.Document;

import java.util.ArrayList;

public class MapsActivity<document> extends FragmentActivity implements OnMapReadyCallback {



    //Add a marker in Carbondale and move the camera
    private static final LatLng CARBONDALE = new LatLng(37.727539,-89.212608);
    private static final LatLng SIUC = new LatLng(37.708385,-89.227455);

    private Marker mCarbondale;
    private Marker mSIUC;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Add some markers to the map, and add a data object to each marker
        mCarbondale = mMap.addMarker(new MarkerOptions()
            .position(CARBONDALE)
            .title("Carbondale")
            .snippet("History: Carbondale was founded in the early 1850's by Daniel Harmon Brush.")
            .draggable(true));
        mSIUC = mMap.addMarker(new MarkerOptions()
                .position(SIUC)
                .title("SIUC")
                .snippet("History: ...........test.............")
                .draggable(true));
        mCarbondale.setTag(0);
        mSIUC.setTag(0);

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(CARBONDALE, 14),5000,null);

    }
}
