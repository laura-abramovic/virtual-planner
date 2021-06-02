package com.android.virtualplanner.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.android.virtualplanner.entities.Schedule
import com.android.virtualplanner.entities.User

data class UserWithSchedule (
    @Embedded
    val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "username"
    )
    val schedules: List<Schedule>
)