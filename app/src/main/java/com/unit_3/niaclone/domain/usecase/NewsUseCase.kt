package com.unit_3.niaclone.domain.usecase

import com.unit_3.niaclone.data.local.UserInterest
import com.unit_3.niaclone.data.model.News
import com.unit_3.niaclone.data.repository.FirebaseRepository
import com.unit_3.niaclone.data.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class NewsUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
    firebaseRepository: FirebaseRepository
) {
    private val newsWithBookmarks: Flow<List<News>> =
        firebaseRepository.getNews().getNewsWithBookmarks(userDataRepository.getBookmarks())

    fun getSubscribedNews(): Flow<List<News>> =
        newsWithBookmarks.mapToUserSearchResult(userDataRepository.getInterests())

    fun getBookmarkedNews() : Flow<List<News>> =
        newsWithBookmarks.getBookmarkedNews()
}

private fun Flow<List<News>>.mapToUserSearchResult(userInterests: Flow<List<UserInterest>>): Flow<List<News>> =
    combine(userInterests) { newsList, userInterestsList ->
        val interests = userInterestsList.map { it.interest }
        newsList.filter { news ->
            news.interest.any { it in interests }
        }
    }

private fun Flow<List<News>>.getBookmarkedNews(): Flow<List<News>> =
    transform { newsList -> emit(newsList.filter { it.isBookmarked }) }

private fun Flow<List<News>>.getNewsWithBookmarks(bookmarks: Flow<List<String>>): Flow<List<News>> =
    combine(bookmarks) { newsList, bookmarkList ->
        newsList.map { news ->
            news.copy(isBookmarked = bookmarkList.contains(news.name))
        }
    }