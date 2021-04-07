package com.example.capstoneproject.core.data.source.local

import com.example.capstoneproject.core.data.source.local.entity.MovieEntity
import com.example.capstoneproject.core.data.source.local.entity.TvEntity
import com.example.capstoneproject.core.data.source.local.room.ContentDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val contentDao: ContentDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = contentDao.getAllMovie()

    fun getBookmarkedMovie(): Flow<List<MovieEntity>> = contentDao.getBookmarkedMovie()

    suspend fun insertMovies(movies: List<MovieEntity>) = contentDao.insertMovies(movies)

    fun setBookmarkedMovie(movie: MovieEntity, newState: Boolean) {
        movie.bookmarked = newState
        contentDao.updateBookmarkedMovie(movie)
    }

    fun getAllTv(): Flow<List<TvEntity>> = contentDao.getAllTv()

    fun getBookmarkedTv(): Flow<List<TvEntity>> = contentDao.getBookmarkedTv()

    suspend fun insertTvs(tvs: List<TvEntity>) = contentDao.insertTvs(tvs)

    fun setBookmarkedTvs(tvs: TvEntity, newState: Boolean) {
        tvs.bookmarked = newState
        contentDao.updateBookmarkedTv(tvs)
    }
}