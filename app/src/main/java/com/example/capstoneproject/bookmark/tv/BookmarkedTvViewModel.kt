package com.example.capstoneproject.bookmark.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstoneproject.core.domain.usecase.ContentUseCase

class BookmarkedTvViewModel(contentUseCase: ContentUseCase) : ViewModel() {
    val tvs = contentUseCase.getBookmarkedTv().asLiveData()
}
