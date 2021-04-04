package com.example.capstoneproject.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.dicoding.tourismapp.core.data.NetworkBoundResource
import com.example.capstoneproject.core.data.source.local.LocalDataSource
import com.example.capstoneproject.core.data.source.remote.RemoteDataSource
import com.example.capstoneproject.core.data.source.remote.network.ApiResponse
import com.example.capstoneproject.core.domain.repository.IContentRepository
import com.example.capstoneproject.core.utils.AppExecutors
import com.example.capstoneproject.core.data.source.mapper.MovieMapper
import com.example.capstoneproject.core.data.source.mapper.TvMapper
import com.example.capstoneproject.core.data.source.remote.response.MovieResponse
import com.example.capstoneproject.core.data.source.remote.response.TvResponse
import com.example.capstoneproject.core.domain.model.Movie
import com.example.capstoneproject.core.domain.model.Tv

class ContentRepository (
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : IContentRepository {

    override fun getAllMovie(): LiveData<Resource<List<Movie>>> =
            object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(appExecutors) {
                override fun loadFromDB(): LiveData<List<Movie>> {
                    return Transformations.map(localDataSource.getAllMovie()) {
                        MovieMapper.movieMapEntitiesToDomain(it)
                    }
                }

                override fun shouldFetch(data: List<Movie>?): Boolean =
                        data == null || data.isEmpty()

                override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                        remoteDataSource.getAllMovie()

                override fun saveCallResult(data: List<MovieResponse>) {
                    val movieList = MovieMapper.movieMapResponsesToEntities(data)
                    localDataSource.insertMovies(movieList)
                }
            }.asLiveData()

    override fun getBookmarkedMovie(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.getBookmarkedMovie()) {
            MovieMapper.movieMapEntitiesToDomain(it)
        }
    }

    override fun setBookmarkMovie(movie: Movie, state: Boolean) {
        val movieEntity = MovieMapper.movieMapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setBookmarkedMovie(movieEntity, state) }
    }

    override fun getAllTv(): LiveData<Resource<List<Tv>>> =
        object : NetworkBoundResource<List<Tv>, List<TvResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Tv>> {
                return Transformations.map(localDataSource.getAllTv()) {
                    TvMapper.tvMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tv>?): Boolean =
                    data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvResponse>>> =
                    remoteDataSource.getAllTv()

            override fun saveCallResult(data: List<TvResponse>) {
                val tvList = TvMapper.tvMapResponsesToEntities(data)
                localDataSource.insertTvs(tvList)
            }
        }.asLiveData()

    override fun getBookmarkedTv(): LiveData<List<Tv>> {
        return Transformations.map(localDataSource.getBookmarkedTv()) {
            TvMapper.tvMapEntitiesToDomain(it)
        }
    }

    override fun setBookmarkTv(tv: Tv, state: Boolean) {
        val tvEntity = TvMapper.tvMapDomainToEntity(tv)
        appExecutors.diskIO().execute { localDataSource.setBookmarkedTvs(tvEntity, state) }
    }
}

