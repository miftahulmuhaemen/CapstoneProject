package com.example.capstoneproject.core.di

import android.app.Application
import androidx.room.Room
import com.example.capstoneproject.core.data.ContentRepository
import com.example.capstoneproject.core.data.source.local.LocalDataSource
import com.example.capstoneproject.core.data.source.local.room.ContentDao
import com.example.capstoneproject.core.data.source.local.room.ContentDatabase
import com.example.capstoneproject.core.data.source.remote.RemoteDataSource
import com.example.capstoneproject.core.domain.usecase.ContentInteractor
import com.example.capstoneproject.core.domain.usecase.ContentUseCase
import com.example.capstoneproject.core.utils.AppExecutors
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {

    fun provideDatabase(application: Application): ContentDatabase {
        return Room.databaseBuilder(application, ContentDatabase::class.java, "localdatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideLocalDao(database: ContentDatabase): ContentDao {
        return database.contentDao()
    }

    fun provideContentUseCase(repository: ContentRepository): ContentUseCase {
        return ContentInteractor(repository)
    }

    single { provideDatabase(androidApplication()) }
    single { provideLocalDao(get()) }
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { AppExecutors() }
    single {
        ContentRepository(
            get(),
            get(),
            get()
        )
    }
    single { provideContentUseCase(get()) }
}