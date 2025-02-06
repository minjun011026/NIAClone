package com.unit_3.niaclone.ui.foryou

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun ForYouView(modifier: Modifier, viewModel: ForYouViewModel = hiltViewModel()){

    LaunchedEffect(Unit) {
        viewModel.fetchUserWithArticles()
    }

    LazyColumn (
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {

    }
}