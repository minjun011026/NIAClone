package com.unit_3.niaclone.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserInterestDao {

    @Query("SELECT * FROM user_interests")
    fun getAllInterests(): Flow<List<UserInterest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInterest(interest: UserInterest)

    @Query("DELETE FROM user_interests WHERE interest = :interestName")
    suspend fun deleteInterest(interestName: String)
}