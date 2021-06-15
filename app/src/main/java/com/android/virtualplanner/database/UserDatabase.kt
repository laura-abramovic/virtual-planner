package com.android.virtualplanner.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.android.virtualplanner.dao.UserDao
import com.android.virtualplanner.entities.*
import com.android.virtualplanner.fragments.TodoFragment

@Database(
    entities = [
        User::class,
        Calendar::class,
        CalendarDay::class,
        Note::class,
        ToDoItem::class,
        Schedule::class,
        ScheduleItem::class
    ],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_db"
                ).fallbackToDestructiveMigration().build().also{
                    INSTANCE = it
                }
            }
        }

    }
}