package com.android.virtualplanner.activities.visuals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.virtualplanner.R
import com.android.virtualplanner.fragments.CalendarFragment
import com.android.virtualplanner.fragments.HomeFragment
import com.android.virtualplanner.fragments.NotesFragment
import com.android.virtualplanner.fragments.TodoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        val fragment = HomeFragment()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                .commit()

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHomeId -> {
                    val fragment = HomeFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                        .commit()
                    true
                }
                R.id.navCalendarId -> {
                    val fragment = CalendarFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                            .commit()
                    true
                }
                R.id.navTodoId -> {
                    val fragment = TodoFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                            .commit()
                    true
                }
                R.id.navNotesId -> {
                    val fragment = NotesFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                            .commit()
                    true
                }
            }
            false
        }
    }
}