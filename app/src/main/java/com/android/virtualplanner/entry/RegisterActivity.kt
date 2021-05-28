package com.android.virtualplanner.entry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.virtualplanner.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_register)

        val singUpButton = findViewById<Button>(R.id.regSingUpButtonId)
        val singInLink = findViewById<TextView>(R.id.regBottomLinkId)

        singUpButton.setOnClickListener {
            Toast.makeText(this, "You are now singed up", Toast.LENGTH_SHORT).show()
        }

        singInLink.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun hideActionBar() {
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {}
    }
}