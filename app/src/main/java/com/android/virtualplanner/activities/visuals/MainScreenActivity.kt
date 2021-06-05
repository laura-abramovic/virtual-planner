package com.android.virtualplanner.activities.visuals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.virtualplanner.R
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.fragments.CalendarFragment
import com.android.virtualplanner.fragments.HomeFragment
import com.android.virtualplanner.fragments.NotesFragment
import com.android.virtualplanner.fragments.TodoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainScreenActivity : AppCompatActivity() {
    var username: String = ""
    private lateinit var dao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        username = intent.getStringExtra("username").toString()
        displayUsername()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // the first fragment that will be shown
        var fragment: Fragment
        fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                .commit()

        bottomNav.setOnNavigationItemSelectedListener { item ->
            fragment = createFragment(item.itemId)
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                    .commit()
            true
        }

    }

    private fun displayUsername() {
        val usernameDisplay = findViewById<TextView>(R.id.usernameDisplayId)
        usernameDisplay.text = this.username
    }

    private fun createFragment(id: Int): Fragment {
        var fragment: Fragment
        fragment = HomeFragment()

        when (id) {
            R.id.navHomeId -> {
                fragment = HomeFragment()
            }
            R.id.navCalendarId -> {
                fragment = CalendarFragment()
            }
            R.id.navTodoId -> {
                fragment = TodoFragment()
            }
            R.id.navNotesId -> {
                fragment = NotesFragment()
            }
        }
        return fragment
    }

}