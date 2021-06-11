package com.android.virtualplanner.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ToDoItem (
    @PrimaryKey(autoGenerate = true)  val id: Long,
    var item: String,
    val username: String // username of whose this to do item is
)