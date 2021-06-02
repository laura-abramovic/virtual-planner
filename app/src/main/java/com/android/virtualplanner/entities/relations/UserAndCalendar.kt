package com.android.virtualplanner.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.android.virtualplanner.entities.Calendar
import com.android.virtualplanner.entities.User

data class UserAndCalendar (
    @Embedded val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "username"
    )
    val calendar: Calendar
)