package com.android.virtualplanner.dao

import androidx.room.*
import com.android.virtualplanner.entities.*
import com.android.virtualplanner.entities.relations.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalendar(calendar: Calendar)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCalendarDay(calendarDay: CalendarDay)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToDoItem(item: ToDoItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSchedule(schedule: Schedule)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertScheduleItem(scheduleItem: ScheduleItem)

    @Delete
    suspend fun deleteToDoItem(item: ToDoItem)

    @Transaction
    @Query("SELECT * FROM user")
    suspend fun getUsers(): List<User>

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUserAndCalendar(username: String): List<UserAndCalendar>

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username" )
    suspend fun getUserWithNotes(username: String): List<UserWithNotes>

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username" )
    suspend fun getUserWithTodos(username: String): List<UserWithTodos>

    @Transaction
    @Query("SELECT * FROM user WHERE username = :username" )
    suspend fun getUserWithSchedule(username: String): List<UserWithSchedule>

    //@Transaction
    //@Query("SELECT * FROM schedule WHERE id = :id" )
    //suspend fun getUserWithNotes(id: Long): List<ScheduleWithScheduleItems>
}