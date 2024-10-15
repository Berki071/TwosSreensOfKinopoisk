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
import androidx.compose.material3.CenterAlignedTopAppBar
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
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

    val robotoFamily = FontFamily(
        Font(R.font.roboto_regular400, FontWeight.Normal),
        Font(R.font.roboto_medium500, FontWeight.Medium),
        Font(R.font.roboto_bold700, FontWeight.Bold)
    )

    Scaffold(
        modifier= modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = filmItem.name ?: "",
                        fontSize = 18.sp,
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis

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
                        .padding(top = 24.dp)
                    ,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    AsyncImage(
                        model = filmItem.image_url,
                        contentDescription = "",
                        error = painterResource(R.drawable.error_img),
                        modifier = Modifier
                            .height(201.dp)
                            .clip(RoundedCornerShape(4.dp))
                        ,
                        contentScale = ContentScale.Fit,
                    )
                }


                Text(
                    text = filmItem.localized_name ?: "" ,
                    fontSize = 26.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 24.dp, start = 16.dp, end= 16.dp),
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "${filmItem.getAllFilmsString()}${filmItem.year} год",
                    fontSize = 16.sp,
                    color = colorResource(R.color.grayText),
                    modifier = Modifier.padding(top = 8.dp, start = 16.dp, end= 16.dp),
                    fontFamily = robotoFamily,
                    fontWeight = FontWeight.Normal
                )

                if (filmItem.rating != null) {
                    Row(modifier = Modifier.padding(top = 10.dp, start = 16.dp, end = 16.dp)) {
                        Text(
                            text = "${filmItem.rating}",
                            fontSize = 24.sp,
                            color = colorResource(R.color.primaryColor),
                            modifier = Modifier
                                .alignByBaseline()
                                .padding(end = 8.dp),
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "КиноПоиск",
                            fontSize = 16.sp,
                            color = colorResource(R.color.primaryColor),
                            modifier = Modifier.alignByBaseline(),
                            fontFamily = robotoFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                if(filmItem.description != null) {
                    Text(
                        text = "${filmItem.description}",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 14.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                        fontFamily = robotoFamily,
                        fontWeight = FontWeight.Normal
                    )
                }
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