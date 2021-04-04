package com.example.capstoneproject.core.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.capstoneproject.core.data.source.local.entity.MovieEntity
import com.example.capstoneproject.core.data.source.local.entity.TvEntity

@Dao
interface ContentDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tv")
    fun getAllTv(): LiveData<List<TvEntity>>

    @Query("SELECT * FROM movie where bookmarked = 1")
    fun getBookmarkedMovie(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM tv where bookmarked = 1")
    fun getBookmarkedTv(): LiveData<List<TvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvs(tvs: List<TvEntity>)

    @Update
    fun updateBookmarkedMovie(movie: MovieEntity)

    @Update
    fun updateBookmarkedTv(tv: TvEntity)
}
