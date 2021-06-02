package com.android.virtualplanner.activities.entry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.room.Dao
import androidx.room.Database
import com.android.virtualplanner.R
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.database.UserDatabase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private var inputUsername: String = ""
    private lateinit var dao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_register)

        dao = UserDatabase.getInstance(this).userDao

        val singUpButton = findViewById<Button>(R.id.regSingUpButtonId)
        val singInLink = findViewById<TextView>(R.id.regBottomLinkId)

        Toast.makeText(this, "You are now singed up", Toast.LENGTH_SHORT).show()

        singUpButton.setOnClickListener {
            var isValidInput = false
            lifecycleScope.launch {
                checkInput()
            }
//            if (isValidInput)
//                Toast.makeText(this, "You are now singed up", Toast.LENGTH_SHORT).show()
//            else
//                Toast.makeText(this, "You are not singed up", Toast.LENGTH_SHORT).show()
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

    private suspend fun checkInput(): Boolean {
        return checkUsername() && checkPassword()
    }

    private suspend fun checkUsername(): Boolean {
        inputUsername = findViewById<TextInputEditText>(R.id.regUsernameEditTextId).text.toString()
        return !isInDatabase(inputUsername) && isValid(inputUsername)
    }

    private suspend fun isInDatabase(username: String): Boolean {
        val users = dao.getUsers()

        for (user in users) {
            if (user.username == username)
                return false
        }
        return true
    }

    private fun isValid(username: String): Boolean {
        val errorMessage = findViewById<TextView>(R.id.regUsernameErrorTextId)

        return if (!isUsernameComplexEnough(username)) {
            errorMessage.setText(R.string.register_screen_username_error_message)
            false
        } else {
            errorMessage.setText(R.string.empty_string)
            true
        }
    }

    private fun isUsernameComplexEnough(username: String): Boolean {
        // regular expression for validating username
        val regex = "^(?=.{3,20}\$)(?![_0-9])(?!.*[_]{2})[a-zA-Z0-9_]+(?<![_])\$".toRegex()
        return username.matches(regex)
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