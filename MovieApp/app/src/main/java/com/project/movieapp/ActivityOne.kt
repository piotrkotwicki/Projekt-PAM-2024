package com.project.movieapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ActivityOne : AppCompatActivity(), OnMapReadyCallback {
    private var mGoogleMap:GoogleMap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)

        val mapFragment = supportFragmentManager
            .findFragmentByID(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val buttonReturnToMain = findViewById<Button>(R.id.buttonReturnToMain)
        buttonReturnToMain.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(0, 0)


        }

        }
    override fun onMapReady(googleMap: GoogleMap) {
        mGoogleMap = googleMap
    }
}