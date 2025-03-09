package com.unit_3.niaclone.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookmark_news")
data class BookmarkNews(
    @PrimaryKey
    val newsName: String
)