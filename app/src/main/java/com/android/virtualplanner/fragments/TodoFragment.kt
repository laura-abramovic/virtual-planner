package com.android.virtualplanner.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.virtualplanner.R
import com.android.virtualplanner.preferences.AppPreferences

class TodoFragment : Fragment() {
    private lateinit var imageAddButton: ImageButton
    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val username = AppPreferences.username

        var root = inflater.inflate(R.layout.fragment_todo, container, false)
        imageAddButton = root.findViewById(R.id.todoAddImageButtonId)

        imageAddButton.setOnClickListener {
            createPopupDialog()
        }

        return root
    }

    private fun createPopupDialog() {
        val view = layoutInflater.inflate(R.layout.popup_add_todo_item, null)
        dialogBuilder = AlertDialog.Builder(activity)

        dialogBuilder.setView(view)
        dialog = dialogBuilder.create()
        dialog.show()

    }
}