package com.android.virtualplanner.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.android.virtualplanner.entities.Calendar
import com.android.virtualplanner.entities.CalendarDay

data class CalendarWithCalendarDays (
    @Embedded
    val calendar: Calendar,
    @Relation(
        parentColumn = "id",
        entityColumn = "calendarId"
    )
    val calendarDays: List<CalendarDay>
)