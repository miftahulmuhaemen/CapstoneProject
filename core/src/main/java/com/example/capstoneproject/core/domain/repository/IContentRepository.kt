package com.example.capstoneproject.core.domain.repository

import com.example.capstoneproject.core.data.Resource
import com.example.capstoneproject.core.domain.model.Movie
import com.example.capstoneproject.core.domain.model.Tv
import kotlinx.coroutines.flow.Flow

interface IContentRepository {

    fun getAllMovie(): Flow<Resource<List<Movie>>>

    fun getBookmarkedMovie(): Flow<List<Movie>>

    fun setBookmarkMovie(movie: Movie, state: Boolean)

    fun getAllTv(): Flow<Resource<List<Tv>>>

    fun getBookmarkedTv(): Flow<List<Tv>>

    fun setBookmarkTv(tv: Tv, state: Boolean)

}
