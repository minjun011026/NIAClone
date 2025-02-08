package com.unit_3.niaclone.data.model

import com.google.firebase.Timestamp

data class News(
    val imageUrl: String = "",
    val name: String = "",
    val date: Timestamp = Timestamp.now(),
    val newsType: String = "",
    val detail: String = "",
    val interest: List<String> = emptyList(),
    val newsUrl: String = ""
)
