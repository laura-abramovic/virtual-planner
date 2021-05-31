package com.android.virtualplanner.activities.visuals

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.virtualplanner.R
import com.android.virtualplanner.fragments.CalendarFragment
import com.android.virtualplanner.fragments.HomeFragment
import com.android.virtualplanner.fragments.NotesFragment
import com.android.virtualplanner.fragments.TodoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

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