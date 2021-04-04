package com.example.capstoneproject.core.data.source.remote.response

import com.squareup.moshi.Json

data class MovieListResponse(
        @field:Json(name = "page") var page: Int?,
        @field:Json(name = "results") var results: List<MovieResponse>?,
        @field:Json(name = "total_results") var totalResults: Int?,
        @field:Json(name = "total_pages") var totalPages: Int?
)