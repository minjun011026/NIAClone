package com.unit_3.niaclone.ui.interests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.unit_3.niaclone.data.repository.TopicRepository
import com.unit_3.niaclone.ui.interests.component.InterestItem

@Composable
fun InterestsView(
    modifier: Modifier
) {
    val interests = TopicRepository.interests

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(interests) { interest ->
            InterestItem(interest)
        }
    }
}
