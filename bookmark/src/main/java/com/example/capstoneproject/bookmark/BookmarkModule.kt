package com.example.capstoneproject.bookmark

import com.example.capstoneproject.bookmark.detail.DetailBookmarkViewModel
import com.example.capstoneproject.bookmark.movie.BookmarkedMovieViewModel
import com.example.capstoneproject.bookmark.tv.BookmarkedTvViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val bookmarkModule = module {
    viewModel { BookmarkedMovieViewModel(get()) }
    viewModel { BookmarkedTvViewModel(get()) }
    viewModel { DetailBookmarkViewModel(get()) }
}