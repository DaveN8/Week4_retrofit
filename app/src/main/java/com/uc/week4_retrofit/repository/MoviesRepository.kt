package com.uc.week4_retrofit.repository

import com.uc.week4_retrofit.retrofit.EndPointApi
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: EndPointApi) {

    suspend fun getNowPlayingData(apiKey: String, language: String, page: Int) =
        api.getNowPlaying(apiKey, language, page)

    suspend fun getMovieDetailsData(apiKey: String, id: Int) =
        api.getMovieDetails(id, apiKey)

//    suspend fun getMovieGenreData(apiKey: String, id: Int) =
//        api.getMovieGenre(id, apiKey)
}