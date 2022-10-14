package com.uc.week4_retrofit.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uc.week4_retrofit.model.Genre
import com.uc.week4_retrofit.model.MovieDetails
import com.uc.week4_retrofit.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.uc.week4_retrofit.model.Result

@HiltViewModel
class MoviesViewModel @Inject constructor(private val repository: MoviesRepository): ViewModel() {

    //get now playing data
    val _nowplaying: MutableLiveData<ArrayList<Result>> by lazy {
        MutableLiveData<ArrayList<Result>>()
    }

    val nowplaying: LiveData<ArrayList<Result>>
    get() = _nowplaying

    fun getNowPlaying(apiKey: String, language: String, page: Int) = viewModelScope.launch {
        repository.getNowPlayingData(apiKey, language, page).let {
            response->
            if (response.isSuccessful){
                _nowplaying.postValue(response.body()?.results as ArrayList<Result>?)
        }else{
            Log.e("Get Data", "Failed!")         }
        }
    }

    //get movie detail
    val _movieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }

    val movieDetails: LiveData<MovieDetails>
        get() = _movieDetails

    fun getMovieDetails(apiKey: String, movieId: Int) = viewModelScope.launch {
        repository.getMovieDetailsData(apiKey, movieId).let {
                response->
            if (response.isSuccessful){
                _movieDetails.postValue(response.body() as MovieDetails)
            }else{
                Log.e("Get Movie Details Data", "Failed!")         }
        }
    }


}