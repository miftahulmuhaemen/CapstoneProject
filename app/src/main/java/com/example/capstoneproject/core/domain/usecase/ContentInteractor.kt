package com.example.capstoneproject.core.domain.usecase

import androidx.lifecycle.LiveData
import com.example.capstoneproject.core.data.Resource
import com.example.capstoneproject.core.domain.model.Movie
import com.example.capstoneproject.core.domain.model.Tv
import com.example.capstoneproject.core.domain.repository.IContentRepository

class ContentInteractor(private val contentRepository: IContentRepository) : ContentUseCase {

    override fun getAllMovie(): LiveData<Resource<List<Movie>>> = contentRepository.getAllMovie()

    override fun getBookmarkedMovie(): LiveData<List<Movie>> = contentRepository.getBookmarkedMovie()

    override fun setBookmarkMovie(movie: Movie, state: Boolean) = contentRepository.setBookmarkMovie(movie, state)

    override fun getAllTv(): LiveData<Resource<List<Tv>>> = contentRepository.getAllTv()

    override fun getBookmarkedTv(): LiveData<List<Tv>> = contentRepository.getBookmarkedTv()

    override fun setBookmarkTv(tv: Tv, state: Boolean) = contentRepository.setBookmarkTv(tv, state)
}