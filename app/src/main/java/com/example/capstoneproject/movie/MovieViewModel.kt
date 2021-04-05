package com.example.capstoneproject.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstoneproject.core.domain.usecase.ContentUseCase

class MovieViewModel(contentUseCase: ContentUseCase) : ViewModel() {
    val movies = contentUseCase.getAllMovie().asLiveData()
}
