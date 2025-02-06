package com.unit_3.niaclone.ui.foryou

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unit_3.niaclone.domain.usecase.GetSavableNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForYouViewModel @Inject constructor(
    private val getSavableNewsUseCase: GetSavableNewsUseCase
) : ViewModel() {
    fun fetchUserWithArticles() {
        viewModelScope.launch {
            val result = getSavableNewsUseCase()
            if (result != null) {
                Log.d("tmp", result.joinToString())
            }
        }
    }
}