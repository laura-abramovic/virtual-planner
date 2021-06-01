package com.android.virtualplanner.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.virtualplanner.data.Note
import com.android.virtualplanner.data.Schedule
import com.android.virtualplanner.data.UserWithNotes
import com.android.virtualplanner.data.UserWithSchedule

interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: Schedule)

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username" )
    suspend fun getUserWithNotes(username: String): List<UserWithNotes>

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username" )
    suspend fun getUserWithSchedule(username: String): List<UserWithSchedule>
}