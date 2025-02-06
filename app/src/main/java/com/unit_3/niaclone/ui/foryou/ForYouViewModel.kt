package com.unit_3.niaclone.ui.foryou

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.unit_3.niaclone.data.local.UserInterest
import com.unit_3.niaclone.data.repository.UserDataRepository
import com.unit_3.niaclone.domain.usecase.GetSavableNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val getSavableNewsUseCase: GetSavableNewsUseCase,
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    val savedInterests: StateFlow<List<UserInterest>> =
        userDataRepository.getInterests().stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    fun fetchUserWithArticles() {
        viewModelScope.launch {
            val result = getSavableNewsUseCase()
            if (result != null) {
                Log.d("tmp", result.joinToString())
            }
        }
    }

    fun saveInterests(interests: List<String>) {
        viewModelScope.launch {
            interests.forEach { interest ->
                userDataRepository.addInterest(interest)
            }
        }
    }
}