package com.example.capstoneproject.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tv(
    val id: Int,
    val posterPath: String?,
    val overview: String?,
    val firstAirDate: String?,
    val name: String?,
    val bookmarked: Boolean
) : Parcelable
