package com.android.virtualplanner.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CalendarDay (
    @PrimaryKey(autoGenerate = true) val id: Long,
    val productivity: Int,
    val calendarId: Long // calendar of whose day it is
)