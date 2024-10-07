package com.example.twossreensofkinopoisk.ui.list_of_movies

import android.util.Log
import androidx.compose.material3.SnackbarHostState
import androidx.lifecycle.ViewModel
import com.example.twossreensofkinopoisk.data.Network.common.Common
import com.example.twossreensofkinopoisk.data.Network.`interface`.RetrofitServices
import com.example.twossreensofkinopoisk.data.Network.model.FilmItem
import com.example.twossreensofkinopoisk.data.Network.model.FilmsResponse
import com.example.twossreensofkinopoisk.ui.list_of_movies.dop_view_and_class.GenreForList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class ListOfMoviesUIState(
    val isShowLoading: Boolean = false,
    val isError: Boolean = false,
    val listOfGenres: List<GenreForList>? = null,
    val selectedGenre: GenreForList? = null,
    val listOfFilmsAll: List<FilmItem>? = null,
    val listOfFilmsShow: List<Pair<FilmItem,FilmItem?>>? = null,
)

class ListOfMoviesVM: ViewModel() {

    private var _uiState: MutableStateFlow<ListOfMoviesUIState> = MutableStateFlow(ListOfMoviesUIState())
    val uiState: StateFlow<ListOfMoviesUIState> = _uiState.asStateFlow()

    var mService: RetrofitServices? = Common.retrofitService

    fun getAllMovieList() {
        isShowLoading(true)
        mService?.getFilms()?.enqueue(object : Callback<FilmsResponse> {
            override fun onFailure(call: Call<FilmsResponse>, t: Throwable) {
                isShowLoading(false)
                isShowErrorMsg(true)
            }

            override fun onResponse(call: Call<FilmsResponse>, response: Response<FilmsResponse>) {
                response.body()?.films?.let{ it ->
                    val setGenres: MutableSet<String> = mutableSetOf()
                    for(i in it){
                        i.genres?.let{
                            setGenres.addAll(i.genres)
                        }
                    }

                    val newListMain = it.sortedBy { it.localized_name }
                    _uiState.update { currentState ->
                        currentState.copy(
                            listOfGenres=  setGenres.toList().sorted().map{ title -> GenreForList(title.substring(0, 1).uppercase() + title.substring(1),false)},
                            listOfFilmsAll= newListMain,
                            isError = false
                        )
                    }

                    chooseFilmsByGenre()

                    isShowLoading(false)
                }
            }
        })
    }

    fun chooseFilmsByGenre(){
        if(_uiState.value.listOfFilmsAll != null) {
            val newList: MutableList<Pair<FilmItem, FilmItem?>> = mutableListOf()

            val listFilms: List<FilmItem>

            if(_uiState.value.selectedGenre == null){
                listFilms = _uiState.value.listOfFilmsAll!!
            }else{
                listFilms= _uiState.value.listOfFilmsAll!!.filter { it.genres?.contains(_uiState.value.selectedGenre!!.title, ignoreCase = true) ?: false}
            }

            for (i in 0 until listFilms.size step 2) {
                newList.add(Pair(listFilms[i], if ((i + 1) < listFilms.size) listFilms[i + 1] else null))
            }

            _uiState.update { currentState ->
                currentState.copy(
                    listOfFilmsShow = newList,
                )
            }

        }else{

            _uiState.update { currentState ->
                currentState.copy(
                    listOfFilmsShow = null,
                    isError = false
                )
            }

        }
    }

    fun isShowErrorMsg(boo:Boolean){
        _uiState.update { currentState ->
            currentState.copy(
                isError = boo
            )
        }
    }

    fun isShowLoading(boo: Boolean){
        _uiState.update { currentState ->
            currentState.copy(
                isShowLoading = boo
            )
        }
    }

    fun clickGenre(item: GenreForList){
        if(_uiState.value.selectedGenre != null && _uiState.value.selectedGenre!!.title == item.title){
            _uiState.update { currentState ->
                currentState.copy(
                    selectedGenre = null
                )
            }
        }else{
            _uiState.update { currentState ->
                currentState.copy(
                    selectedGenre = item
                )
            }
        }

        chooseFilmsByGenre()
    }

    fun List<String>.contains(s: String, ignoreCase: Boolean = false): Boolean {

        return any { it.equals(s, ignoreCase) }
    }

}