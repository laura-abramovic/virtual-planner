package com.android.virtualplanner.activities.entry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
            if (checkInput()) {
                Toast.makeText(this, "You are now singed up", Toast.LENGTH_SHORT).show()
            }
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

    private fun checkInput(): Boolean {
        return checkUsername() && checkPassword()
    }

    private fun checkUsername(): Boolean {
        //check if username is in database
        return true
    }

    private fun checkPassword(): Boolean {
        val password = findViewById<TextInputEditText>(R.id.regPasswordEditTextId).text.toString()

        if (!checkComplexity(password))
            return false

        val repeatedPassword = findViewById<TextInputEditText>(R.id.regRepeatPasswordEditTextId).text.toString()

        if (!checkMatching(password, repeatedPassword))
            return false

        return true
    }

    private fun checkComplexity(password: String): Boolean {
        val errorMessage = findViewById<TextView>(R.id.regComplexityErrorTextId)

        return if (!isPasswordComplexEnough(password)) {
            errorMessage.setText(R.string.register_screen_complexity_error_message)
            false
        } else {
            errorMessage.setText(R.string.empty_string)
            true
        }
    }

    private fun isPasswordComplexEnough(password: String): Boolean {
        // regular expression for validating password
        val regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$".toRegex()
        return password.matches(regex)
    }

    private fun checkMatching(password: String, repeatedPassword: String): Boolean {
        val errorMessage = findViewById<TextView>(R.id.regMatchingErrorTextId)

        return if (password != repeatedPassword) {
            errorMessage.setText(R.string.register_screen_matching_error_message)
            false
        } else {
            errorMessage.setText(R.string.empty_string)
            true
        }
    }


}