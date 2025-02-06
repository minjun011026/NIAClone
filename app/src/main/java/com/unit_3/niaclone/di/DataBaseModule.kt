package com.unit_3.niaclone.di

import android.content.Context
import androidx.room.Room
import com.unit_3.niaclone.data.local.AppDatabase
import com.unit_3.niaclone.data.local.UserInterestDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserInterestDao(database: AppDatabase): UserInterestDao {
        return database.userInterestDao()
    }

}