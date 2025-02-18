package com.unit_3.niaclone.ui.foryou.component

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.unit_3.niaclone.data.model.News


@Composable
fun NewsContent(news: News) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { openCustomTab(context, news.newsUrl) },
        shape = RoundedCornerShape(16.dp),
    ) {
        Column {
            AsyncImage(
                model = news.imageUrl,
                contentDescription = null
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.DarkGray)
                    .padding(14.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.BookmarkBorder,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                    Text(
                        text = news.name,
                        fontSize = 30.sp,
                        color = Color.White
                    )
                }
                Text(
                    text = news.date.toDate().toString(),
                    fontSize = 12.sp,
                    color = Color.White
                )
                Text(
                    text = news.detail,
                    color = Color.White
                )
                LazyRow {
                    items(news.interest) {
                        interest ->
                        AssistChip(
                            modifier = Modifier
                                .padding(2.dp),
                            onClick ={},
                            label = {
                                Text(
                                    text = interest,
                                    fontSize = 12.sp
                                )
                             },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = Color.Gray,
                                labelColor = Color.White
                            ),
                            border = null,
                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                }
            }
        }
    }
}

fun openCustomTab(context: Context, url: String) {
    val intent = CustomTabsIntent.Builder().build()
    intent.launchUrl(context, Uri.parse(url))
}