package com.unit_3.niaclone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserInterest::class, BookmarkNews::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userInterestDao(): UserInterestDao

    abstract fun bookmarkDao(): BookmarkDao
}