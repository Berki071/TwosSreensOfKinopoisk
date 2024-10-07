package com.example.twossreensofkinopoisk.data.Network.model

data class FilmItem(
    val id: Int? = null,
    val localized_name: String? = null,
    val name: String? = null,
    val year: Int? = null,
    val rating: Double? = null,
    val image_url: String? = null,
    val description: String? = null,
    val genres: List<String>? = null
){

    fun getAllFilmsString() : String {
        var str = ""
        genres?.let {
            for (i in it) {
                str += i
                str +=", "
            }
        }

        return str
    }
}
