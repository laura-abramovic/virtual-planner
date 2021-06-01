package com.android.virtualplanner.data

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithNotes (
    @Embedded val user: User,
    @Relation(
        parentColumn = "username",
        entityColumn = "username"
    )
    val notes: List<Note>
)