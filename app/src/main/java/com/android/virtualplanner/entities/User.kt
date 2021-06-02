package com.android.virtualplanner.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey(autoGenerate = false) val username: String,
    val password: String
)