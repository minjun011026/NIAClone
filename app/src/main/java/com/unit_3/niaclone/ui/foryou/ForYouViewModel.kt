package com.unit_3.niaclone.ui.foryou

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unit_3.niaclone.data.model.News
import com.unit_3.niaclone.data.repository.UserDataRepository
import com.unit_3.niaclone.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    getInterestNewsUseCase: NewsUseCase,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    val savedInterests: StateFlow<List<News>?> = getInterestNewsUseCase.getSubscribedNews().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = null
        )

    fun saveInterests(interests: List<String>) {
        viewModelScope.launch {
            interests.forEach { interest ->
                userDataRepository.addInterest(interest)
            }
        }
    }

    fun toggleBookmarks(newsName: String, isBookmarked: Boolean) {
        viewModelScope.launch {
            if (isBookmarked)
                userDataRepository.deleteBookmark(newsName)
            else
                userDataRepository.addBookmark(newsName)
        }
    }
}