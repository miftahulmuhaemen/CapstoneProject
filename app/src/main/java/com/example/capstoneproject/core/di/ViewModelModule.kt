package com.example.capstoneproject.core.di

import com.example.capstoneproject.bookmark.movie.BookmarkedMovieViewModel
import com.example.capstoneproject.bookmark.tv.BookmarkedTvViewModel
import com.example.capstoneproject.detail.DetailViewModel
import com.example.capstoneproject.movie.MovieViewModel
import com.example.capstoneproject.tv.TvViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { BookmarkedTvViewModel(get()) }
    viewModel { BookmarkedMovieViewModel(get()) }
}