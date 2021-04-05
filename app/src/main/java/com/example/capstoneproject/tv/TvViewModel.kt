package com.example.capstoneproject.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.capstoneproject.core.domain.usecase.ContentUseCase

class TvViewModel(contentUseCase: ContentUseCase) : ViewModel() {
    val tvs = contentUseCase.getAllTv().asLiveData()
}
