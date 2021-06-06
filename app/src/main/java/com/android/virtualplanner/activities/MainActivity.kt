package com.android.virtualplanner.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.android.virtualplanner.R
import com.android.virtualplanner.activities.entry.LoginActivity
import com.android.virtualplanner.activities.entry.RegisterActivity
import com.android.virtualplanner.activities.visuals.MainScreenActivity
import com.android.virtualplanner.preferences.AppPreferences

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (AppPreferences.isLoggedIn) {
            val intent = Intent(this, MainScreenActivity::class.java)
            startActivity(intent)
        }

        val loginButton = findViewById<Button>(R.id.loginButtonId)

        loginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val registerButton = findViewById<Button>(R.id.registerButtonId)

        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}