package com.unit_3.niaclone.ui.interests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.unit_3.niaclone.data.repository.TopicRepository
import com.unit_3.niaclone.ui.interests.component.InterestItem
import com.unit_3.niaclone.ui.navigation.component.Dest

@Composable
fun InterestsView(
    modifier: Modifier,
    navController : NavHostController,
    viewModel: InterestsViewModel = hiltViewModel()
) {
    val interests = TopicRepository.interests
    val userInterest by viewModel.userInterest.collectAsState()

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(interests) { interest ->
            val isInterest = userInterest.any { it.interest == interest }

            InterestItem(
                name = interest,
                isInterest = isInterest,
                onItemClick = { name ->
                    navController.navigate(Dest.InterestDetail(name))
                },
                onToggleClick = { viewModel.toggleInterest(interest, isInterest) }
            )
        }
    }
}