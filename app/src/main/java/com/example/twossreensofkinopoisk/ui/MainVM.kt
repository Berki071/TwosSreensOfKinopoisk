package com.example.twossreensofkinopoisk.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.twossreensofkinopoisk.data.Network.model.FilmItem

class MainVM: ViewModel() {
    private val mutableSelectedFilm = MutableLiveData<FilmItem?>()
    val selectedItem: LiveData<FilmItem?> get() = mutableSelectedFilm

    fun selectItem(item: FilmItem?) {
        mutableSelectedFilm.value = item
    }

}