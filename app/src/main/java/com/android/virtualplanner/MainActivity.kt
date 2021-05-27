package com.android.virtualplanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getStartedButton = findViewById<Button>(R.id.HomeScreenStartButton)

        getStartedButton.setOnClickListener {
            Toast.makeText(this, "Getting started", Toast.LENGTH_SHORT).show()
        }
    }
}