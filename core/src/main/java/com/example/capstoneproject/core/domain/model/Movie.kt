package com.example.capstoneproject.core.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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