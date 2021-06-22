package com.android.virtualplanner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.virtualplanner.R
import com.android.virtualplanner.activities.visuals.MainScreenActivity
import com.android.virtualplanner.entities.ToDoItem
import com.android.virtualplanner.fragments.TodoFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking

class CustomToDoListAdapter(private val itemList: ArrayList<ToDoItem>): RecyclerView.Adapter<CustomToDoListAdapter.ViewHolder>() {
    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomToDoListAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.list_row_todo, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.listRowTodoNameId).text = itemList[position].item

        holder.view.findViewById<ImageButton>(R.id.listRowTodoDeleteButtonId).setOnClickListener {

            //TODO: popraviti delete

            itemList.removeAt(position)
            notifyItemRemoved(position)
        }

    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return itemList.size
    }

    //the class is holding the list view
    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}