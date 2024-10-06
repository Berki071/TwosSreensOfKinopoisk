package com.example.twossreensofkinopoisk.ui.list_of_movies.dop_view_and_class

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.twossreensofkinopoisk.data.Network.model.FilmItem

@Composable
fun FilmBoxListItem(
    data: Pair<FilmItem, FilmItem?>,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier){
        Box(){
            FilmListItem(item= data.first)
        }

        Box(){
            data.second?.let{
                FilmListItem(item = it)
            }
        }
    }

}

@Preview
@Composable
private fun FilmBoxItemPreview() {
    val t1= FilmItem(
            id = 326,
            localized_name = "Побег из Шоушенка",
            name = "The Shawshank Redemption",
            year = 1996,
            rating = 9.196,
            image_url = "https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg",
            description = "Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.",
            genres = listOf("драма")
    )

    val t2 =FilmItem(
            id = 326,
            localized_name = "Побег из Шоушенка",
            name = "The Shawshank Redemption",
            year = 1996,
            rating = 9.196,
            image_url = "https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg",
            description = "Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.",
            genres = listOf("драма")
    )
    FilmBoxListItem(data= Pair(t1, t2 ) )
}