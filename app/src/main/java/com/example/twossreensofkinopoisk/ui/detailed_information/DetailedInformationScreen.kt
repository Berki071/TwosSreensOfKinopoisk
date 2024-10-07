package com.example.twossreensofkinopoisk.ui.detailed_information

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.twossreensofkinopoisk.R
import com.example.twossreensofkinopoisk.data.Network.model.FilmItem
import com.example.twossreensofkinopoisk.ui.list_of_movies.ListOfMoviesVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedInformationScreen(
    filmItem: FilmItem,
    clickBack: ()->Unit,
    modifier: Modifier = Modifier,
    manVM: DetailedInformationVM = viewModel()
) {
    val uiState = manVM.uiState.collectAsState()

    Scaffold(
        modifier= modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = filmItem.name ?: "",
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon={ IconButton({ clickBack()}) { Icon(Icons.Filled.ArrowBack, contentDescription = "Назад")}},
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.primaryColor),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )

        },
    ) {

        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top= 32.dp)
                    ,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    AsyncImage(
                        model = filmItem.image_url,
                        contentDescription = "",
                        error = painterResource(R.drawable.error_img),
                        modifier = Modifier
                            .height(300.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .aspectRatio(2f / 3f),
                        contentScale = ContentScale.Fit,
                    )
                }


                Text(filmItem.localized_name ?: "" , fontSize = 14.sp, modifier = Modifier)

                Text( "${filmItem.getAllFilmsString()}${filmItem.year} год", fontSize = 14.sp, modifier = Modifier)

                Text("${filmItem.rating} КиноПоиск", fontSize = 14.sp, modifier = Modifier)

                Text("${filmItem.description}", fontSize = 14.sp, modifier = Modifier)
            }
        }
    }

}

@Preview
@Composable
private fun DetailedInformationScreenPreview() {
    DetailedInformationScreen(filmItem= FilmItem(
        id = 326,
        localized_name = "Побег из Шоушенка",
        name = "The Shawshank Redemption",
        year = 1996,
        rating = 9.196,
        image_url = "https://st.kp.yandex.net/images/film_iphone/iphone360_326.jpg",
        description = "Успешный банкир Энди Дюфрейн обвинен в убийстве собственной жены и ее любовника. Оказавшись в тюрьме под названием Шоушенк, он сталкивается с жестокостью и беззаконием, царящими по обе стороны решетки. Каждый, кто попадает в эти стены, становится их рабом до конца жизни. Но Энди, вооруженный живым умом и доброй душой, отказывается мириться с приговором судьбы и начинает разрабатывать невероятно дерзкий план своего освобождения.",
        genres = listOf("драма")
    ), clickBack= {})
}