package com.android.virtualplanner.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.time.Duration

@Entity
data class ScheduleItem (
    @PrimaryKey(autoGenerate = true) val id: Long,
    var title: String,
    var duration: Duration,
    var fixed: Boolean,
    var timeStart: Time?,
    val scheduleId: Long // id of the schedule
)