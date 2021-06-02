package com.android.virtualplanner.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.android.virtualplanner.entities.Schedule
import com.android.virtualplanner.entities.ScheduleItem

data class ScheduleWithScheduleItems (
    @Embedded
    val schedule: Schedule,
    @Relation(
        parentColumn = "id",
        entityColumn = "scheduleId"
    )
    val scheduleItems: List<ScheduleItem>
)