package com.unit_3.niaclone.data.repository

import com.unit_3.niaclone.data.local.UserInterest
import com.unit_3.niaclone.data.local.UserInterestDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val dao: UserInterestDao
){

    fun getInterests(): Flow<List<UserInterest>> = dao.getAllInterests()

    suspend fun addInterest(interest: String) {
        dao.insertInterest(UserInterest(interest = interest))
    }

    suspend fun deleteInterestById(interestName: String) {
        dao.deleteInterest(interestName)
    }
}