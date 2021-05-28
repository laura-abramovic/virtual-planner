package com.android.virtualplanner.entry

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.virtualplanner.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_login)

        val forgotPassLink = findViewById<TextView>(R.id.forgotPasswordTextId)
        val singInButton = findViewById<Button>(R.id.singInButtonId)
        val singUpLink = findViewById<TextView>(R.id.bottomLinkId)

        forgotPassLink.setOnClickListener {
            Toast.makeText(this, "We will send you an email once we implement that", Toast.LENGTH_SHORT).show()
        }

        singInButton.setOnClickListener {
            Toast.makeText(this, "You are now singed in", Toast.LENGTH_SHORT).show()
        }

        singUpLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun hideActionBar() {
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}
    }



}