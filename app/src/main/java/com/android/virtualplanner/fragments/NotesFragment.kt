package com.android.virtualplanner.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.virtualplanner.R
import com.android.virtualplanner.preferences.AppPreferences

class NotesFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val username = AppPreferences.username

        Toast.makeText(activity, "From notes: $username", Toast.LENGTH_LONG).show()

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notes, container, false)
    }
}