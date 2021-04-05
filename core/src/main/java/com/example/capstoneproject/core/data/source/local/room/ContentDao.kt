package com.example.capstoneproject.core.data.source.local.room

import androidx.room.*
import com.example.capstoneproject.core.data.source.local.entity.MovieEntity
import com.example.capstoneproject.core.data.source.local.entity.TvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ContentDao {

    @Query("SELECT * FROM movie")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv")
    fun getAllTv(): Flow<List<TvEntity>>

    @Query("SELECT * FROM movie where bookmarked = 1")
    fun getBookmarkedMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tv where bookmarked = 1")
    fun getBookmarkedTv(): Flow<List<TvEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvs(tvs: List<TvEntity>)

    @Update
    fun updateBookmarkedMovie(movie: MovieEntity)

    @Update
    fun updateBookmarkedTv(tv: TvEntity)
}
