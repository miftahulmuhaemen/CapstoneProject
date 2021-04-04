package com.example.capstoneproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.capstoneproject.core.data.Resource
import com.example.capstoneproject.core.domain.model.Movie
import com.example.capstoneproject.core.domain.model.Tv

interface ContentUseCase {
    fun getAllMovie(): LiveData<Resource<List<Movie>>>

    fun getBookmarkedMovie(): LiveData<List<Movie>>

    fun setBookmarkMovie(movie: Movie, state: Boolean)

    fun getAllTv(): LiveData<Resource<List<Tv>>>

    fun getBookmarkedTv(): LiveData<List<Tv>>

    fun setBookmarkTv(tv: Tv, state: Boolean)
}