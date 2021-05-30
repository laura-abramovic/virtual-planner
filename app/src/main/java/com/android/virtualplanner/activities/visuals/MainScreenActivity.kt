package com.android.virtualplanner.activities.visuals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.virtualplanner.R
import com.android.virtualplanner.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navHomeId -> {
//                    val fragment = HomeFragment
//                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
//                        .commit()
                    true
                }
                R.id.navCalendarId -> {
                    true
                }
                R.id.navTodoId -> {
                    true
                }
                R.id.navNotesId -> {
                    true
                }
            }
            false
        }
    }

//    private val navListener =
//        BottomNavigationView.OnNavigationItemSelectedListener {
//            fun onNavigationItemSelected
//        }
}