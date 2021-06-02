package com.android.virtualplanner.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note (
    @PrimaryKey(autoGenerate = true)  val id: Long,
    var title: String,
    var content: String,
    val username: String // username of whose this note is
)