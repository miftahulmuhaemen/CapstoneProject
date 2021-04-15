package com.example.capstoneproject.core.di

import android.app.Application
import androidx.room.Room
import com.example.capstoneproject.core.data.ContentRepository
import com.example.capstoneproject.core.data.source.local.LocalDataSource
import com.example.capstoneproject.core.data.source.local.room.ContentDao
import com.example.capstoneproject.core.data.source.local.room.ContentDatabase
import com.example.capstoneproject.core.data.source.remote.RemoteDataSource
import com.example.capstoneproject.core.domain.usecase.ContentInteract
import com.example.capstoneproject.core.domain.usecase.ContentUseCase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {

    fun provideDatabase(application: Application): ContentDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("capstoneProject".toCharArray())
        val factory = SupportFactory(passphrase)
        return Room.databaseBuilder(application, ContentDatabase::class.java, "localdatabase")
            .fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }

    fun provideLocalDao(database: ContentDatabase): ContentDao {
        return database.contentDao()
    }

    fun provideContentUseCase(repository: ContentRepository): ContentUseCase {
        return ContentInteract(repository)
    }

    single { provideDatabase(androidApplication()) }
    single { provideLocalDao(get()) }
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single {
        ContentRepository(
            get(),
            get()
        )
    }
    single { provideContentUseCase(get()) }
}