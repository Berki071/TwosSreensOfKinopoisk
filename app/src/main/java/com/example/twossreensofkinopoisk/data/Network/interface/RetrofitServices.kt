package com.example.twossreensofkinopoisk.data.Network.`interface`

import com.example.twossreensofkinopoisk.data.Network.model.FilmsResponse
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitServices {
    @GET("sequeniatesttask/films.json")
    fun getFilms() : Call<FilmsResponse>
}
