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
import com.android.virtualplanner.activities.visuals.MainScreenActivity
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.database.UserDatabase
import com.android.virtualplanner.entities.User
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.mindrot.jbcrypt.BCrypt

class LoginActivity : AppCompatActivity() {
    private var inputUsername: String = ""
    private lateinit var dao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val singInButton = findViewById<Button>(R.id.loginSingInButtonId)
        val singUpLink = findViewById<TextView>(R.id.loginBottomLinkId)

        dao = UserDatabase.getInstance(this).userDao

        singInButton.setOnClickListener {
            var users = runBlocking { dao.getUsers() } // get users from database

            var error = findViewById<TextView>(R.id.loginErrorId)
            if (!isCorrectInput(users)) {
                error.setText(R.string.login_screen_error)
            } else {
                error.setText(R.string.empty_string)
                Toast.makeText(this, "You are now singed in", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainScreenActivity::class.java)
                intent.putExtra("username", inputUsername)
                startActivity(intent)
            }


        }

        singUpLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private  fun isCorrectInput(users: List<User>): Boolean {
        inputUsername = findViewById<TextInputEditText>(R.id.loginUsernameEditTextId).text.toString()

        for (user in users) {
            if (user.username == inputUsername) {
                val inputPassword = findViewById<TextInputEditText>(R.id.loginPasswordEditTextId).text.toString()
                return BCrypt.checkpw(inputPassword, user.password) // check if password is valid
            }
        }

        return false
    }

}