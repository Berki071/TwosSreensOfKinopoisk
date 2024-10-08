package com.example.twossreensofkinopoisk.ui.list_of_movies.dop_view_and_class

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.twossreensofkinopoisk.R
import com.example.twossreensofkinopoisk.data.Network.model.FilmItem

@Composable
fun FilmListItem(
    item: FilmItem,
    onClick: (FilmItem) -> Unit,
    modifier: Modifier = Modifier
) {

    val robotoFamily = FontFamily(
        Font(R.font.roboto_regular400, FontWeight.Normal),
        Font(R.font.roboto_medium500, FontWeight.Medium),
        Font(R.font.roboto_bold700, FontWeight.Bold)
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(item)
            }
    ){
        AsyncImage(
            model = item.image_url,
            contentDescription = "",
            error = painterResource(R.drawable.error_img),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(160f/222f)
                .padding(bottom = 8.dp)
                .clip(RoundedCornerShape(4.dp))
            ,
            contentScale = ContentScale.Crop,
        )
        Text(
            text = item.localized_name ?: "",
            maxLines = 2,
            minLines = 2,
            fontSize = 16.sp,
            overflow = TextOverflow.Ellipsis,
            fontFamily = robotoFamily,
            fontWeight = FontWeight.Bold
        )
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
        ),
        onClick= {}
    )
}