package com.example.twossreensofkinopoisk.ui.list_of_movies.dop_view_and_class

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.example.twossreensofkinopoisk.R
import com.example.twossreensofkinopoisk.data.Network.model.FilmItem

@Composable
fun FilmListItem(
    item: FilmItem,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier) {
        AsyncImage(
            model = item.image_url,
            contentDescription = "",
            error = painterResource(R.drawable.error_img)
        )
        Text(text = item.localized_name ?: "")
    }

}

@Preview
@Composable
private fun FilmItemPreview() {
    FilmListItem(
        item = FilmItem(
            id = 326,
            localized_name = "Побег из Шоушенка",
            name = "The Shawshank Redemption",
            year = 1996,
            rating = 9.196,
            image_url = "https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg",
            description = "Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.",
            genres = listOf("драма")
        )
    )
}