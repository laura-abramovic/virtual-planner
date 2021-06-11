package com.android.virtualplanner.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.PrimaryKey
import com.android.virtualplanner.R
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.database.UserDatabase
import com.android.virtualplanner.entities.Note
import com.android.virtualplanner.entities.ToDoItem
import com.android.virtualplanner.preferences.AppPreferences
import kotlinx.coroutines.runBlocking

class TodoFragment : Fragment() {
    private lateinit var imageAddButton: ImageButton
    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    private lateinit var inputItem: EditText
    private lateinit var saveButton: Button

    private lateinit var dao: UserDao
    private lateinit var username: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        username = AppPreferences.username

        var root = inflater.inflate(R.layout.fragment_todo, container, false)
        imageAddButton = root.findViewById(R.id.todoAddImageButtonId)

        dao = UserDatabase.getInstance(requireActivity().applicationContext).userDao

        imageAddButton.setOnClickListener {
            createPopupDialog()
        }

        return root
    }

    private fun createPopupDialog() {
        val view = layoutInflater.inflate(R.layout.popup_add_todo_item, null)
        dialogBuilder = AlertDialog.Builder(activity)

        inputItem = view.findViewById(R.id.popupTodoItemId)
        saveButton = view.findViewById(R.id.popupTodoSaveButtonId)

        saveButton.setOnClickListener {
            saveNote()
        }

        dialogBuilder.setView(view)
        dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun saveNote() {
        var todoText = inputItem.text.toString()

        if (todoText.isEmpty()) {
            return
        }

        var newToDoItem = ToDoItem(0, todoText, username)

        var todoItems =  runBlocking { dao.getUserWithTodos(username = username) }



    }
}