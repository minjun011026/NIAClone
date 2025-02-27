package com.unit_3.niaclone.ui.foryou

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.unit_3.niaclone.ui.foryou.component.InterestSelectContent
import com.unit_3.niaclone.ui.foryou.component.NewsContent

@Composable
fun ForYouView(modifier: Modifier, viewModel: ForYouViewModel = hiltViewModel()) {

    val newsList by viewModel.savedInterests.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        when {
            newsList == null -> item {
                CircularProgressIndicator()
            }
            newsList!!.isEmpty() -> item {
                InterestSelectContent(viewModel)
            }
            else -> items(newsList!!) { news ->
                NewsContent(news)
            }
        }
    }
}