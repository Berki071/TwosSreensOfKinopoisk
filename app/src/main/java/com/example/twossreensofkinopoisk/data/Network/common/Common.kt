package com.example.twossreensofkinopoisk.data.Network.common

import com.example.twossreensofkinopoisk.data.Network.`interface`.RetrofitServices
import com.example.twossreensofkinopoisk.data.Network.retrofit.RetrofitClient

object Common {
    private val BASE_URL = "https://s3-eu-west-1.amazonaws.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)

}