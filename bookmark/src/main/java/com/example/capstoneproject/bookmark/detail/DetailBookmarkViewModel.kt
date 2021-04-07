package com.example.capstoneproject.bookmark.detail

import androidx.lifecycle.ViewModel
import com.example.capstoneproject.core.domain.model.Movie
import com.example.capstoneproject.core.domain.model.Tv
import com.example.capstoneproject.core.domain.usecase.ContentUseCase

class DetailBookmarkViewModel(private val contentUseCase: ContentUseCase) : ViewModel() {
    fun setBookmarkMovie(movie: Movie, state: Boolean) =
        contentUseCase.setBookmarkMovie(movie, state)

    fun setBookmarkTv(tv: Tv, state: Boolean) =
        contentUseCase.setBookmarkTv(tv, state)
}
