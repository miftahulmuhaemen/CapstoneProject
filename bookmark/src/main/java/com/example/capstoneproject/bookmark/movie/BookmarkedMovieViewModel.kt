package com.example.capstoneproject.bookmark.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstoneproject.core.domain.usecase.ContentUseCase

class BookmarkedMovieViewModel(contentUseCase: ContentUseCase) : ViewModel() {
    val movies = contentUseCase.getBookmarkedMovie().asLiveData()
}
