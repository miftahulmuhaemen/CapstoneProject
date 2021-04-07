package com.example.capstoneproject.core.data.source.remote.response

import com.squareup.moshi.Json

data class TvResponse(
    @field:Json(name = "id") var id: Int,
    @field:Json(name = "poster_path") var posterPath: String?,
    @field:Json(name = "overview") var overview: String?,
    @field:Json(name = "first_air_date") var firstAirDate: String?,
    @field:Json(name = "name") var name: String?
)