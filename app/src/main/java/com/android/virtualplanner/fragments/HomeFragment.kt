package com.android.virtualplanner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.virtualplanner.R
import com.android.virtualplanner.activities.visuals.MainScreenActivity
import com.android.virtualplanner.preferences.AppPreferences

class HomeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val username = AppPreferences.username

        Toast.makeText(activity, "From home: $username", Toast.LENGTH_LONG).show()

        view?.findViewById<TextView>(R.id.homeTextId)?.text = username

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}