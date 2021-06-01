package com.android.virtualplanner.data

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithSchedule (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "username"
    )
    val schedules: List<Schedule>
)