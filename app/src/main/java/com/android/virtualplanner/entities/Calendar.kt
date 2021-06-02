package com.android.virtualplanner.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Calendar (
    @PrimaryKey (autoGenerate = true) val id: Long,
    val dates: List<Int>,
    val username: String // username of whose this calendar is
)