package com.aregyan.github.di

import com.aregyan.github.BuildConfig
import com.aregyan.github.network.UserDetailsService
import com.aregyan.github.network.UserListService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

//Network module dependency
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    //This function provides Http Client
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    //This function provides Retrofit client
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://api.github.com/")
        .client(okHttpClient)
        .build()

    //This function provides Api Service
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): UserListService =
        retrofit.create(UserListService::class.java)

    //This function provides User Details service api
    @Provides
    @Singleton
    fun provideUserDetailsService(retrofit: Retrofit): UserDetailsService =
        retrofit.create(UserDetailsService::class.java)

}