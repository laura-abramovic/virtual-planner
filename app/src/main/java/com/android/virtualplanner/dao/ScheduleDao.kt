package com.android.virtualplanner.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.virtualplanner.data.ScheduleItem
import com.android.virtualplanner.data.ScheduleWithScheduleItems

interface ScheduleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduleItem(scheduleItem: ScheduleItem)

    @Transaction
    @Query("SELECT * FROM schedule WHERE id = :id" )
    suspend fun getUserWithNotes(id: Long): List<ScheduleWithScheduleItems>
}