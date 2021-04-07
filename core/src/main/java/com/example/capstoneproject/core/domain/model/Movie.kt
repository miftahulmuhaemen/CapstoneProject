package com.example.capstoneproject.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,
    val posterPath: String?,
    val overview: String?,
    val releaseDate: String?,
    val title: String?,
    val bookmarked: Boolean
) : Parcelable