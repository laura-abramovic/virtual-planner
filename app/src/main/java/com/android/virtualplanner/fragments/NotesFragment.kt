package com.android.virtualplanner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.virtualplanner.R
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.database.UserDatabase
import com.android.virtualplanner.preferences.AppPreferences

class NotesFragment : Fragment() {
    private lateinit var dao: UserDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val username = AppPreferences.username

        var root = inflater.inflate(R.layout.fragment_notes, container, false)

        Toast.makeText(activity, "From notes: $username", Toast.LENGTH_LONG).show()

        // Inflate the layout for this fragment
        return root


    }
}

