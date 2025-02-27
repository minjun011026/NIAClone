package com.unit_3.niaclone.ui.interests

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unit_3.niaclone.data.repository.UserDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class InterestsViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository
) : ViewModel() {

    val userInterest = userDataRepository.getInterests().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun toggleInterest(interest: String, isInterest : Boolean) {
        viewModelScope.launch {
            if (isInterest) {
                userDataRepository.deleteInterestById(interest)
            } else {
                userDataRepository.addInterest(interest)
            }
        }
    }
}