package com.android.virtualplanner.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.android.virtualplanner.entities.ToDoItem
import com.android.virtualplanner.entities.User

data class UserWithTodos (
        @Embedded val user: User,
        @Relation(
                parentColumn = "username",
                entityColumn = "username"
        )
        val todoItems: List<ToDoItem>
)