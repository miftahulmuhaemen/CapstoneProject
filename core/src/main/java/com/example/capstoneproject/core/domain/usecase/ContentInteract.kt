package com.example.capstoneproject.core.domain.usecase

import com.example.capstoneproject.core.data.Resource
import com.example.capstoneproject.core.domain.model.Movie
import com.example.capstoneproject.core.domain.model.Tv
import com.example.capstoneproject.core.domain.repository.IContentRepository
import kotlinx.coroutines.flow.Flow

class ContentInteract(private val contentRepository: IContentRepository) : ContentUseCase {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> = contentRepository.getAllMovie()

    override fun getBookmarkedMovie(): Flow<List<Movie>> = contentRepository.getBookmarkedMovie()

    override fun setBookmarkMovie(movie: Movie, state: Boolean) =
        contentRepository.setBookmarkMovie(movie, state)

    override fun getAllTv(): Flow<Resource<List<Tv>>> = contentRepository.getAllTv()

    override fun getBookmarkedTv(): Flow<List<Tv>> = contentRepository.getBookmarkedTv()

    override fun setBookmarkTv(tv: Tv, state: Boolean) = contentRepository.setBookmarkTv(tv, state)
}