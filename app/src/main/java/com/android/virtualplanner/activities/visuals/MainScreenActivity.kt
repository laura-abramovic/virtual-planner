package com.android.virtualplanner.activities.visuals

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.android.virtualplanner.R
import com.android.virtualplanner.activities.MainActivity
import com.android.virtualplanner.activities.menu.ProfileActivity
import com.android.virtualplanner.activities.menu.SettingsActivity
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.database.UserDatabase
import com.android.virtualplanner.fragments.CalendarFragment
import com.android.virtualplanner.fragments.HomeFragment
import com.android.virtualplanner.fragments.NotesFragment
import com.android.virtualplanner.fragments.TodoFragment
import com.android.virtualplanner.preferences.AppPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainScreenActivity : AppCompatActivity() {
    private var username: String = ""
    private lateinit var menuButton: ImageButton

    private lateinit var dao: UserDao


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        dao = UserDatabase.getInstance(this).userDao
        username = AppPreferences.username

        initTopMenu()
        initFragments()
    }

    private fun initTopMenu() {
        displayUsername()
        createPopupMenu()
    }

    private fun displayUsername() {
        val usernameDisplay = findViewById<TextView>(R.id.usernameDisplayId)
        usernameDisplay.text = this.username
    }

    private fun createPopupMenu() {
        menuButton = findViewById(R.id.menuButtonImageId)
        val popupMenu = PopupMenu(this, menuButton)
        popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
        setMenuListeners(popupMenu)

        menuButton.setOnClickListener {
            popupMenu.show()
        }
    }

    private fun setMenuListeners(popupMenu: PopupMenu) {
        popupMenu.setOnMenuItemClickListener { menuItem ->
            var activity = chooseAction(menuItem.itemId)

            val intent = Intent(this, activity!!::class.java)
            startActivity(intent)

            true
        }
    }

    private fun chooseAction(id: Int): Activity? {
        var activity: Activity? = null

        when (id) {
            R.id.menuProfileId -> {
                activity = ProfileActivity()
            }
            R.id.menuSettingsId -> {
                activity = SettingsActivity()
            }
            R.id.menuLogoutId -> {
                clearPreferences()
                activity = MainActivity()
            }
        }

        return activity
    }


    private fun clearPreferences() {
        AppPreferences.username = ""
        AppPreferences.isLoggedIn = false
    }

    private fun initFragments() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // the first fragment that will be shown
        startFragment(R.id.navHomeId)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            startFragment(item.itemId)
            true
        }
    }

    private fun startFragment(id: Int) {
        val fragment = createFragment(id)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment, fragment.javaClass.simpleName)
                .commit()
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