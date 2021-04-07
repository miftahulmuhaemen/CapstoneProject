package com.example.capstoneproject.core.data.source.mapper

import com.example.capstoneproject.core.data.source.local.entity.TvEntity
import com.example.capstoneproject.core.data.source.remote.response.TvResponse
import com.example.capstoneproject.core.domain.model.Tv


object TvMapper {

    fun tvMapResponsesToEntities(input: List<TvResponse>): List<TvEntity> {
        val tvList = ArrayList<TvEntity>()
        input.map {
            val tourism = TvEntity(
                id = it.id,
                posterPath = it.posterPath,
                overview = it.overview,
                firstAirDate = it.firstAirDate,
                name = it.name,
                bookmarked = false
            )
            tvList.add(tourism)
        }
        return tvList
    }

    fun tvMapEntitiesToDomain(input: List<TvEntity>): List<Tv> =
        input.map {
            Tv(
                id = it.id,
                posterPath = it.posterPath,
                overview = it.overview,
                firstAirDate = it.firstAirDate,
                name = it.name,
                bookmarked = it.bookmarked
            )
        }

    fun tvMapDomainToEntity(input: Tv) = TvEntity(
        id = input.id,
        posterPath = input.posterPath,
        overview = input.overview,
        firstAirDate = input.firstAirDate,
        name = input.name,
        bookmarked = input.bookmarked
    )
}