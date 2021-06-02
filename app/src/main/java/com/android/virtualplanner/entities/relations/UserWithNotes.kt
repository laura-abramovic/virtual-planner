package com.android.virtualplanner.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.android.virtualplanner.entities.Note
import com.android.virtualplanner.entities.User

data class UserWithNotes (
    @Embedded val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "username"
    )
    val notes: List<Note>
)