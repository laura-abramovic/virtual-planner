package com.android.virtualplanner.activities.entry

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.virtualplanner.R
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.database.UserDatabase
import com.android.virtualplanner.entities.User
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.runBlocking
import org.mindrot.jbcrypt.BCrypt

class RegisterActivity : AppCompatActivity() {
    private var inputUsername: String = ""
    private var inputPassword: String = ""
    private lateinit var dao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideActionBar()
        setContentView(R.layout.activity_register)

        dao = UserDatabase.getInstance(this).userDao

        val singUpButton = findViewById<Button>(R.id.regSingUpButtonId)
        val singInLink = findViewById<TextView>(R.id.regBottomLinkId)

        singUpButton.setOnClickListener {
            var users = runBlocking { dao.getUsers() } // get users from database

            if (checkInput(users)) {
                saveUserToDb()
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

    private fun checkInput(users: List<User>): Boolean {
        return checkUsername(users) && checkPassword()
    }

    private fun checkUsername(users: List<User>): Boolean {
        inputUsername = findViewById<TextInputEditText>(R.id.regUsernameEditTextId).text.toString()
        return !isInDatabase(inputUsername, users) && isValid(inputUsername)
    }

    private fun isInDatabase(username: String, users: List<User>): Boolean {
        val errorMessage = findViewById<TextView>(R.id.regUsernameErrorTextId)

        for (user in users) {
            if (user.username == username)
                errorMessage.setText(R.string.register_screen_username_already_exists_message)
                return true
        }

        errorMessage.setText(R.string.empty_string)
        return false
    }

    private fun isValid(username: String): Boolean {
        val errorMessage = findViewById<TextView>(R.id.regUsernameErrorTextId)

        return if (username.isEmpty()) {
            errorMessage.setText(R.string.register_screen_username_cannot_be_empty_message)
            false
        } else if (!isUsernameComplexEnough(username)) {
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

        inputPassword = password
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

    private fun saveUserToDb() {
        val passwordHash = BCrypt.hashpw(inputPassword, BCrypt.gensalt())
        val user = User(inputUsername, passwordHash)
        runBlocking { dao.insertUser(user) }
    }
}