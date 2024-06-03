package com.project.movieapp

import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ActivityOne : AppCompatActivity(), OnMapReadyCallback {
    private var mGoogleMap: GoogleMap? = null
    private lateinit var userLastLocation: Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var PERMISSION_REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.mapFragment) as SupportMapFragment
        getUserLocation()
        mapFragment.getMapAsync(this)

        val buttonReturnToMain = findViewById<Button>(R.id.buttonReturnToMain)
        buttonReturnToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)


        }

        }
    private fun getUserLocation(){
        if(ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_CODE)
            return
        }

        mGoogleMap?.isMyLocationEnabled ?:  true

        fusedLocationProviderClient.lastLocation.addOnSuccessListener (this){location:Location ->
            userLastLocation = location
            val userLatLong = LatLng(location.latitude, location.longitude)
            mGoogleMap?.addMarker(MarkerOptions().position(userLatLong).title("Your Marker"))
            mGoogleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(userLatLong, 10f))
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
    }
}