package com.example.capstoneproject.core.data.source.remote.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

data class MovieResponse(
        @field:Json(name = "id") var id: Int,
        @field:Json(name = "poster_path") var posterPath: String?,
        @field:Json(name = "overview") var overview: String?,
        @field:Json(name = "release_date") var releaseDate: String?,
        @field:Json(name = "title") var title: String?
)