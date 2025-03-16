package com.unit_3.niaclone.ui.saved

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.unit_3.niaclone.ui.foryou.component.NewsContent

@Composable
fun SavedView(
    modifier: Modifier = Modifier,
    viewModel: SavedViewModel = hiltViewModel()
) {
    val newsList by viewModel.bookmarkNews.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        items(newsList.orEmpty()) { news ->
            NewsContent(
                news = news,
                onBookmarkClicked = { name, isBookmarked ->
                    viewModel.toggleBookmarks(name, isBookmarked)
                }
            )
        }
    }

}