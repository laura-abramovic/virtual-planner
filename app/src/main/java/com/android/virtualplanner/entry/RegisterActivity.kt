package com.android.virtualplanner.entry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.virtualplanner.R
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_register)

        val singUpButton = findViewById<Button>(R.id.regSingUpButtonId)
        val singInLink = findViewById<TextView>(R.id.regBottomLinkId)

        singUpButton.setOnClickListener {
            //checkInput()
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

    private fun checkInput() {

//        val passwordEditText = findViewById<TextInputEditText>(R.id.regPasswordTextFieldId)
//        val repeatedPasswordEditText = findViewById<TextInputEditText>(R.id.regRepeatPasswordTextFieldId)
//
//        val password = passwordEditText.text.toString()
//        val repeatedPassword = repeatedPasswordEditText.text.toString()
//
//        if (password == repeatedPassword) {
//            Toast.makeText(this, "Passwords match", Toast.LENGTH_SHORT).show()
//        } else {
//            Toast.makeText(this, "Passwords dont match", Toast.LENGTH_SHORT).show()
//        }


    }


}