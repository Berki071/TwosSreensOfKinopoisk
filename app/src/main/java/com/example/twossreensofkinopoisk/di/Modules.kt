package com.example.twossreensofkinopoisk.di

import com.example.twossreensofkinopoisk.ui.MainVM
import com.example.twossreensofkinopoisk.ui.detailed_information.DetailedInformationVM
import com.example.twossreensofkinopoisk.ui.list_of_movies.ListOfMoviesVM
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { ListOfMoviesVM() }
    viewModel { MainVM() }
    viewModel { DetailedInformationVM() }
}