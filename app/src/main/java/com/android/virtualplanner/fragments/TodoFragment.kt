package com.android.virtualplanner.fragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.virtualplanner.R
import com.android.virtualplanner.adapters.CustomToDoListAdapter
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.database.UserDatabase
import com.android.virtualplanner.entities.ToDoItem
import com.android.virtualplanner.preferences.AppPreferences
import kotlinx.coroutines.runBlocking

class TodoFragment : Fragment() {
    private lateinit var imageAddButton: ImageButton
    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog

    private lateinit var inputItem: EditText
    private lateinit var saveButton: Button

    private lateinit var noRemindersText: TextView

    private lateinit var dao: UserDao
    private lateinit var username: String

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CustomToDoListAdapter

    private lateinit var itemList: ArrayList<ToDoItem>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        username = AppPreferences.username
        dao = UserDatabase.getInstance(requireActivity().applicationContext).userDao

        var root = inflater.inflate(R.layout.fragment_todo, container, false)

        noRemindersText = root.findViewById(R.id.todoNoRemindersTextId)
        imageAddButton = root.findViewById(R.id.todoAddImageButtonId)

        loadToDoItems()

        if (itemList.isNotEmpty()) {
            noRemindersText.visibility = View.GONE
        }

        imageAddButton.setOnClickListener {
            createPopupDialog()
        }

        recyclerView = root.findViewById(R.id.todoRwId)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CustomToDoListAdapter(itemList)
        }


        return root
    }

    private fun loadToDoItems() {
        var users =  runBlocking { dao.getUserWithTodos(username = username) }
        itemList = users[0].todoItems as ArrayList<ToDoItem>
    }

    private fun createPopupDialog() {
        val view = layoutInflater.inflate(R.layout.popup_add_todo_item, null)
        dialogBuilder = AlertDialog.Builder(activity)

        inputItem = view.findViewById(R.id.popupTodoItemId)
        saveButton = view.findViewById(R.id.popupTodoSaveButtonId)

        saveButton.setOnClickListener {
            saveToDoItem()
        }

        dialogBuilder.setView(view)
        dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun saveToDoItem() {
        var todoText = inputItem.text.toString()

        if (todoText.isEmpty()) {
            return
        }

        var newToDoItem = ToDoItem(0, todoText, username)

        runBlocking { dao.insertToDoItem(newToDoItem) }

        var index = itemList.size
        itemList.add(index, newToDoItem)

        dialog.dismiss()
        Toast.makeText(activity, R.string.toast_reminder_added, Toast.LENGTH_SHORT)
    }
}