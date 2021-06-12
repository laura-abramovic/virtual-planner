package com.android.virtualplanner.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.android.virtualplanner.R
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.database.UserDatabase
import com.android.virtualplanner.entities.ToDoItem
import com.android.virtualplanner.preferences.AppPreferences
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.runBlocking

class NotesFragment : Fragment() {
    private lateinit var dao: UserDao
    private lateinit var addButton: MaterialButton

    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    private lateinit var inputTitle: EditText
    private lateinit var inputText: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val username = AppPreferences.username

        var root = inflater.inflate(R.layout.fragment_notes, container, false)

        addButton = root.findViewById(R.id.notesAddButtonId)
        addButton.setOnClickListener {
            Toast.makeText(activity, "Add pls", Toast.LENGTH_LONG).show()
            createPopupDialog()
        }

        // Inflate the layout for this fragment
        return root


    }

    private fun createPopupDialog() {
        val view = layoutInflater.inflate(R.layout.popup_add_note, null)
        dialogBuilder = AlertDialog.Builder(activity)

        inputTitle = view.findViewById(R.id.popupNoteTitleId)
        inputText = view.findViewById(R.id.popupNoteTextInputId)
        saveButton = view.findViewById(R.id.popupTodoSaveButtonId)

        saveButton.setOnClickListener {
            //saveToDoItem()
        }

        dialogBuilder.setView(view)
        dialog = dialogBuilder.create()
        dialog.show()
    }

//    private fun save Note() {
//        var todoText = inputItem.text.toString()
//
//        if (todoText.isEmpty()) {
//            return
//        }
//
//        var newToDoItem = ToDoItem(0, todoText, username)
//
//        var todoItems =  runBlocking { dao.getUserWithTodos(username = username) }
//    }
}

