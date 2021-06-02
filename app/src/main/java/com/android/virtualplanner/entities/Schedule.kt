package com.android.virtualplanner.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Schedule (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val username: String // username of whose this schedule is
)