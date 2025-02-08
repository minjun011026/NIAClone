package com.unit_3.niaclone.domain.usecase

import com.unit_3.niaclone.data.local.UserInterest
import com.unit_3.niaclone.data.model.News
import com.unit_3.niaclone.data.repository.NewsRepository
import com.unit_3.niaclone.data.repository.UserDataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetInterestNewsUseCase @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Flow<List<News>> =
        newsRepository.getNews().mapToUserSearchResult(userDataRepository.getInterests())
}

private fun Flow<List<News>>.mapToUserSearchResult(userInterests: Flow<List<UserInterest>>): Flow<List<News>> =
    combine(userInterests) { newsList, userInterestsList ->
        val interests = userInterestsList.map { it.interest }
        newsList.filter { news ->
            news.interest.any { it in interests }
        }
    }