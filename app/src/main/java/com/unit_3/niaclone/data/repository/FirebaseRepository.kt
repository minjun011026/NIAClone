package com.unit_3.niaclone.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.unit_3.niaclone.data.model.News
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    firestore: FirebaseFirestore
) {
    private val newsCollection = firestore.collection("news")

    fun getNews(): Flow<List<News>> = callbackFlow {
        val subscription = newsCollection.addSnapshotListener { snapshot, error ->
            if (error != null) {
                close(error)
                return@addSnapshotListener
            }
            val newsList = snapshot?.toObjects(News::class.java) ?: emptyList()
            trySend(newsList).isSuccess
        }
        awaitClose { subscription.remove() }
    }
}

//News 추가용 임시 코드 최종 사용은 X
//item {
//    Button(onClick = {
//        val news = News(
//            imageUrl = "https://developer.android.com/static/images/shared/android-logo-verticallockup_primary.png?hl=ko",
//            name = "Headlines News",
//            newsType = "Article",
//            detail = "Headlines News이다.",
//            interest = listOf("Headlines", "Performance"),
//            newsUrl = "https://developer.android.com/about/versions/15/get-qpr?hl=ko"
//        )
//        viewModel.saveNews(news)
//    }) {
//        Text("뉴스 추가")
//    }
//}
//item {
//    Button(onClick = {
//        val news = News(
//            imageUrl = "https://developer.android.com/static/images/shared/android-logo-verticallockup_primary.png?hl=ko",
//            name = "Performance News",
//            newsType = "Video",
//            detail = "재미있는 Performance Video",
//            interest = listOf("UI", "Performance"),
//            newsUrl = "https://www.youtube.com/watch?v=IHeF0UqXg9w"
//        )
//        viewModel.saveNews(news)
//    }) {
//        Text("뉴스 추가")
//    }
//}
//item {
//    Button(onClick = {
//        val news = News(
//            imageUrl = "https://developer.android.com/static/images/shared/android-logo-verticallockup_primary.png?hl=ko",
//            name = "Architecture News",
//            newsType = "Video",
//            detail = "재미있는 아키텍처",
//            interest = listOf("Architecture", "Compose"),
//            newsUrl = "https://www.youtube.com/watch?v=IHeF0UqXg9w"
//        )
//        viewModel.saveNews(news)
//    }) {
//        Text("뉴스 추가")
//    }
//}