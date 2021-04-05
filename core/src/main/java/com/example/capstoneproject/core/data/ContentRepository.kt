package com.example.capstoneproject.core.data

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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ContentRepository (
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : IContentRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
            object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
                override fun loadFromDB(): Flow<List<Movie>> {
                    return localDataSource.getAllMovie().map {
                        MovieMapper.movieMapEntitiesToDomain(it)
                    }
                }

                override fun shouldFetch(data: List<Movie>?): Boolean =
                        data == null || data.isEmpty()

                override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                        remoteDataSource.getAllMovie()

                override suspend fun saveCallResult(data: List<MovieResponse>) {
                    val movieList = MovieMapper.movieMapResponsesToEntities(data)
                    localDataSource.insertMovies(movieList)
                }
            }.asFlow()

    override fun getBookmarkedMovie(): Flow<List<Movie>> {
        return localDataSource.getBookmarkedMovie().map {
            MovieMapper.movieMapEntitiesToDomain(it)
        }
    }

    override fun setBookmarkMovie(movie: Movie, state: Boolean) {
        val movieEntity = MovieMapper.movieMapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setBookmarkedMovie(movieEntity, state) }
    }

    override fun getAllTv(): Flow<Resource<List<Tv>>> =
        object : NetworkBoundResource<List<Tv>, List<TvResponse>>() {
            override fun loadFromDB(): Flow<List<Tv>> {
                return localDataSource.getAllTv().map {
                    TvMapper.tvMapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Tv>?): Boolean =
                    data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvResponse>>> =
                    remoteDataSource.getAllTv()

            override suspend fun saveCallResult(data: List<TvResponse>) {
                val tvList = TvMapper.tvMapResponsesToEntities(data)
                localDataSource.insertTvs(tvList)
            }
        }.asFlow()

    override fun getBookmarkedTv(): Flow<List<Tv>> {
        return localDataSource.getBookmarkedTv().map {
            TvMapper.tvMapEntitiesToDomain(it)
        }
    }

    override fun setBookmarkTv(tv: Tv, state: Boolean) {
        val tvEntity = TvMapper.tvMapDomainToEntity(tv)
        appExecutors.diskIO().execute { localDataSource.setBookmarkedTvs(tvEntity, state) }
    }
}

