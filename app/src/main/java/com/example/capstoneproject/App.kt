package com.example.capstoneproject

import android.app.Application
import com.example.capstoneproject.bookmark.movie.BookmarkedMovieViewModel
import com.example.capstoneproject.bookmark.tv.BookmarkedTvViewModel
import com.example.capstoneproject.core.di.networkModule
import com.example.capstoneproject.core.di.repositoryModule
import com.example.capstoneproject.detail.DetailViewModel
import com.example.capstoneproject.movie.MovieViewModel
import com.example.capstoneproject.tv.TvViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, module {
                viewModel { MovieViewModel(get()) }
                viewModel { TvViewModel(get()) }
                viewModel { DetailViewModel(get()) }
                viewModel { BookmarkedTvViewModel(get()) }
                viewModel { BookmarkedMovieViewModel(get()) }
            }))
        }
    }
}
