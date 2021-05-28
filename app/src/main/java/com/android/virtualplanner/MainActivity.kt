package com.android.virtualplanner

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.virtualplanner.visuals.ToDoListActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getStartedButton = findViewById<Button>(R.id.HomeScreenStartButton)

        getStartedButton.setOnClickListener {
            Toast.makeText(this, "Getting started", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, ToDoListActivity::class.java)
            startActivity(intent)
        }
    }
}