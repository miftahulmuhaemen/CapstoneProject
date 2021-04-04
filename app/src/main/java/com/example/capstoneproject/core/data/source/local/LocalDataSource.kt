package com.example.capstoneproject.core.data.source.local

import androidx.lifecycle.LiveData
import com.example.capstoneproject.core.data.source.local.entity.MovieEntity
import com.example.capstoneproject.core.data.source.local.entity.TvEntity
import com.example.capstoneproject.core.data.source.local.room.ContentDao

class LocalDataSource (private val contentDao: ContentDao) {

    fun getAllMovie(): LiveData<List<MovieEntity>> = contentDao.getAllMovie()

    fun getBookmarkedMovie(): LiveData<List<MovieEntity>> = contentDao.getBookmarkedMovie()

    fun insertMovies(movies: List<MovieEntity>) = contentDao.insertMovies(movies)

    fun setBookmarkedMovie(movie: MovieEntity, newState: Boolean) {
        movie.bookmarked = newState
        contentDao.updateBookmarkedMovie(movie)
    }

    fun getAllTv(): LiveData<List<TvEntity>> = contentDao.getAllTv()

    fun getBookmarkedTv(): LiveData<List<TvEntity>> = contentDao.getBookmarkedTv()

    fun insertTvs(tvs: List<TvEntity>) = contentDao.insertTvs(tvs)

    fun setBookmarkedTvs(tvs: TvEntity, newState: Boolean) {
        tvs.bookmarked = newState
        contentDao.updateBookmarkedTv(tvs)
    }
}