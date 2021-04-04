package com.example.capstoneproject.core.data.source.mapper

import com.example.capstoneproject.core.data.source.local.entity.MovieEntity
import com.example.capstoneproject.core.data.source.remote.response.MovieResponse
import com.example.capstoneproject.core.domain.model.Movie


object MovieMapper {

    fun movieMapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val tourism = MovieEntity(
                    id = it.id,
                    posterPath = it.posterPath,
                    overview = it.overview,
                    releaseDate = it.releaseDate,
                    title = it.title,
                    bookmarked = false
            )
            movieList.add(tourism)
        }
        return movieList
    }

    fun movieMapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
            input.map {
                Movie(
                        id = it.id,
                        posterPath = it.posterPath,
                        overview = it.overview,
                        releaseDate = it.releaseDate,
                        title = it.title,
                        bookmarked = it.bookmarked
                )
            }

    fun movieMapDomainToEntity(input: Movie) = MovieEntity(
            id = input.id,
            posterPath = input.posterPath,
            overview = input.overview,
            releaseDate = input.releaseDate,
            title = input.title,
            bookmarked = input.bookmarked
    )
}