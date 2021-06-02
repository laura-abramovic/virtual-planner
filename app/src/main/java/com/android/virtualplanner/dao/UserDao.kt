package com.android.virtualplanner.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.android.virtualplanner.entities.Calendar
import com.android.virtualplanner.entities.Note
import com.android.virtualplanner.entities.Schedule
import com.android.virtualplanner.entities.User
import com.android.virtualplanner.entities.relations.UserAndCalendar
import com.android.virtualplanner.entities.relations.UserWithNotes
import com.android.virtualplanner.entities.relations.UserWithSchedule

interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalendar(calendar: Calendar)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: Schedule)

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUserAndCalendar(username: String): List<UserAndCalendar>

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username" )
    suspend fun getUserWithNotes(username: String): List<UserWithNotes>

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username" )
    suspend fun getUserWithSchedule(username: String): List<UserWithSchedule>
}