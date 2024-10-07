package com.example.twossreensofkinopoisk.ui.list_of_movies

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.twossreensofkinopoisk.R
import com.example.twossreensofkinopoisk.data.Network.model.FilmItem
import com.example.twossreensofkinopoisk.ui.list_of_movies.dop_view_and_class.FilmBoxListItem
import com.example.twossreensofkinopoisk.ui.list_of_movies.dop_view_and_class.GenreItem
import com.example.twossreensofkinopoisk.ui.list_of_movies.dop_view_and_class.Title

//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfMoviesScreen(
    selectFilm: (FilmItem)->Unit,
    modifier: Modifier = Modifier,
    mainVM: ListOfMoviesVM = viewModel()
) {

    val uiState = mainVM.uiState.collectAsState()
    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) { mainVM.getAllMovieList() }

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    if (uiState.value.isError) {
        LaunchedEffect(uiState.value.isError) {
            try {
                val result = snackbarHostState.showSnackbar(
                    message = "Ошибка подключения сети",
                    actionLabel = "Повторить".uppercase()
                )

                when (result) {
                    SnackbarResult.Dismissed -> {}
                    SnackbarResult.ActionPerformed -> {
                        mainVM.getAllMovieList()
                    }
                }
            } finally {
                //mainVM.isShowErrorMsg(false)
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Фильмы",
                        fontSize = 22.sp,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {},
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(R.color.primaryColor),
                    titleContentColor = Color.White
                )
            )
        },
        modifier = modifier
    ) { it ->
        Box(modifier = Modifier.padding(it)) {

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                uiState.value.listOfGenres?.let { listGenre ->
                    item  {
                        Title(title = "Жанры")
                    }
                    items(listGenre) { gen ->
                        GenreItem(
                            item = gen,
                            selectedGenre = uiState.value.selectedGenre,
                            onClick = { item -> mainVM.clickGenre(item) }
                        )
                    }
                }

                uiState.value.listOfFilmsShow?.let{ listFilms ->
                    item  {
                        Title(title= "Фильмы")
                    }

                    items(listFilms) { gen ->
                        FilmBoxListItem(
                            data = gen,
                            onClick = {itemFilm ->
                                selectFilm(itemFilm)
                            }
                        )
                    }
                }
            }
        }

        if (uiState.value.listOfFilmsAll == null) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

    }

}

@Preview
@Composable
private fun ListOfMoviesScreenPreview() {
    ListOfMoviesScreen(selectFilm= {})
}