package com.example.capstoneproject.core.domain.usecase

import com.example.capstoneproject.core.data.Resource
import com.example.capstoneproject.core.domain.model.Movie
import com.example.capstoneproject.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface ContentUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getBookmarkedMovie(): Flow<List<Movie>>

    fun setBookmarkMovie(movie: Movie, state: Boolean)

    fun getAllTv(): Flow<Resource<List<Tv>>>

    fun getBookmarkedTv(): Flow<List<Tv>>

    fun setBookmarkTv(tv: Tv, state: Boolean)
}