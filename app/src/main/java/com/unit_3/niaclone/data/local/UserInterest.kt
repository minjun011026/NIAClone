package com.unit_3.niaclone.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_interests")
data class UserInterest(
    @PrimaryKey
    val interest: String
)
