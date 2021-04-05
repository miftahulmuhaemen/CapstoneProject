package com.example.capstoneproject.core.data.source.remote.network

import com.example.capstoneproject.core.BuildConfig.API_KEY
import com.example.capstoneproject.core.data.source.remote.response.MovieListResponse
import com.example.capstoneproject.core.data.source.remote.response.TvListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("tv/top_rated?api_key=${API_KEY}&language=en-US&page=1")
    suspend fun getAllTv(): TvListResponse

    @GET("movie/top_rated?api_key=${API_KEY}&language=en-US&page=1")
    suspend fun getAllMovie(): MovieListResponse
}
