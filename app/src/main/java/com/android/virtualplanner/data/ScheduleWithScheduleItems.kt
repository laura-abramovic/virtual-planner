package com.android.virtualplanner.data

import androidx.room.Embedded
import androidx.room.Relation

data class ScheduleWithScheduleItems (
    @Embedded
    val schedule: Schedule,
    @Relation(
        parentColumn = "id",
        entityColumn = "scheduleId"
    )
    val scheduleItems: List<ScheduleItem>
)