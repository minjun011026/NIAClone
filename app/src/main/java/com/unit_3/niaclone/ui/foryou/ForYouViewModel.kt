package com.unit_3.niaclone.ui.foryou

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unit_3.niaclone.data.model.News
import com.unit_3.niaclone.data.repository.UserDataRepository
import com.unit_3.niaclone.domain.usecase.GetInterestNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    getInterestNewsUseCase: GetInterestNewsUseCase,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    val savedInterests: StateFlow<List<News>?> = getInterestNewsUseCase().stateIn(
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
}