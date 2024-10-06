package com.example.twossreensofkinopoisk.ui.detailed_information

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.twossreensofkinopoisk.ui.list_of_movies.ListOfMoviesVM


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedInformationScreen(

    modifier: Modifier = Modifier,
    manVM: DetailedInformationVM = viewModel()
) {
    val uiState = manVM.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title= { Text("METANIT.COM", fontSize = 22.sp) },
                navigationIcon={ IconButton({ }) { Icon(Icons.Filled.Menu, contentDescription = "Меню") } },
                actions={},
                colors= TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray,
                    titleContentColor = Color.LightGray,
                    navigationIconContentColor = Color.LightGray,
                    actionIconContentColor = Color.LightGray))
        },
    ){
        Text("Hello METANIT.COM", fontSize = 28.sp, modifier = Modifier.padding(it))
    }
}

@Preview
@Composable
private fun DetailedInformationScreenPreview() {
    DetailedInformationScreen()
}