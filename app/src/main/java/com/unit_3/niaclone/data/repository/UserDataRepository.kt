package com.unit_3.niaclone.data.repository

import com.unit_3.niaclone.data.local.BookmarkDao
import com.unit_3.niaclone.data.local.BookmarkNews
import com.unit_3.niaclone.data.local.UserInterest
import com.unit_3.niaclone.data.local.UserInterestDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val userInterestDao: UserInterestDao,
    private val bookmarkDao: BookmarkDao
){

    fun getInterests(): Flow<List<UserInterest>> = userInterestDao.getAllInterests()

    suspend fun addInterest(interest: String) {
        userInterestDao.insertInterest(UserInterest(interest = interest))
    }

    suspend fun deleteInterestById(interestName: String) {
        userInterestDao.deleteInterest(interestName)
    }

    fun getBookmarks(): Flow<List<String>> = bookmarkDao.getBookmarks()

    suspend fun addBookmark(newsName: String) {
        bookmarkDao.insertBookmark(BookmarkNews(newsName = newsName))
    }

    suspend fun deleteBookmark(newsName: String) {
        bookmarkDao.deleteBookmark(newsName)
    }
}