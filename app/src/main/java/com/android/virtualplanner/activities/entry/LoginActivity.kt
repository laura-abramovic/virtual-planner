package com.android.virtualplanner.activities.entry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.android.virtualplanner.R
import com.android.virtualplanner.database.UserDatabase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val dao = UserDatabase.getInstance(this).userDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_login)

        val forgotPassLink = findViewById<TextView>(R.id.loginForgotPasswordTextId)
        val singInButton = findViewById<Button>(R.id.loginSingInButtonId)
        val singUpLink = findViewById<TextView>(R.id.loginBottomLinkId)

        //val dao = UserDatabase.getInstance(this).userDao

        forgotPassLink.setOnClickListener {
            Toast.makeText(this, "We will send you an email once we implement that", Toast.LENGTH_SHORT).show()
        }

        singInButton.setOnClickListener {
            val inputUsername = findViewById<TextInputEditText>(R.id.loginUsernameEditTextId).text.toString()
            var exists = false

            lifecycleScope.launch {
                isCorrectInput()

            }

            if (!exists) {
                Toast.makeText(this, "Username or password invalid", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "You are now singed in", Toast.LENGTH_SHORT).show()
            }

            //Toast.makeText(this, "You are now singed in", Toast.LENGTH_SHORT).show()
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

    private suspend fun isCorrectInput(): Boolean {
        val inputUsername = findViewById<TextInputEditText>(R.id.loginUsernameEditTextId).text.toString()
        val users = dao.getUsers()

        for (user in users) {
            if (user.username == inputUsername) {
                val inputPassword = findViewById<TextInputEditText>(R.id.loginPasswordEditTextId).text.toString()
                val storedPassword = user.password

                return isCorrectPassword(inputPassword, storedPassword)
            }
        }

        return false
    }

    private fun isCorrectPassword(pass1: String, pass2: String): Boolean {
        return false
    }



}