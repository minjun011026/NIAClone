package com.unit_3.niaclone.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookmarkDao {

    @Query("SELECT newsName FROM bookmark_news")
    fun getBookmarks(): Flow<List<String>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBookmark(newsName: BookmarkNews)

    @Query("DELETE FROM bookmark_news WHERE newsName = :newsName")
    suspend fun deleteBookmark(newsName: String)
}