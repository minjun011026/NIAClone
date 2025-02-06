package com.unit_3.niaclone.ui.foryou

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.unit_3.niaclone.ui.foryou.component.InterestSelectContent

@Composable
fun ForYouView(modifier: Modifier, viewModel: ForYouViewModel = hiltViewModel()) {

    val savedInterests by viewModel.savedInterests.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserWithArticles()
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if (savedInterests.isEmpty())
            item {
                InterestSelectContent(viewModel)
            }
        else {

        }
    }
}