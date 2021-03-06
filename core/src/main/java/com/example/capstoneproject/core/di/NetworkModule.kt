package com.example.capstoneproject.core.di

import com.example.capstoneproject.core.BuildConfig
import com.example.capstoneproject.core.data.source.remote.network.ApiService
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { makeRetrofitService() }
    single { makeOkHttpClient() }
    single { makeLoggingInterceptor() }
}

private fun makeRetrofitService(): ApiService {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(makeOkHttpClient())
        .addConverterFactory(MoshiConverterFactory.create())
        .build().create(ApiService::class.java)
}

private fun makeOkHttpClient(): OkHttpClient {
    val certificatePinner = CertificatePinner.Builder()
        .add(BuildConfig.CERTIFICATE_URL, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
        .add(BuildConfig.CERTIFICATE_URL, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
        .add(BuildConfig.CERTIFICATE_URL, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
        .build()
    return OkHttpClient.Builder()
        .addInterceptor(makeLoggingInterceptor())
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(90, TimeUnit.SECONDS)
        .certificatePinner(certificatePinner)
        .build()
}

private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level =
        HttpLoggingInterceptor.Level.BODY
    return logging
}