package com.unit_3.niaclone.domain.usecase

import com.unit_3.niaclone.data.local.UserInterest
import com.unit_3.niaclone.data.repository.NewsRepository
import com.unit_3.niaclone.data.repository.UserDataRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetSavableNewsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val userDataRepository: UserDataRepository
) {
    suspend operator fun invoke() : List<UserInterest>? {
        return try {
            val interests: List<UserInterest> = userDataRepository.getInterests().first()
            return interests
        } catch (e: Exception) {
            null
        }
    }
}