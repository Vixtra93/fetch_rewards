package com.project.fetchrewards.di

import com.project.fetchrewards.BuildConfig
import com.project.fetchrewards.remote.FetchService
import com.project.fetchrewards.remote.FetchRemoteDataSource
import com.project.fetchrewards.repository.FetchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    fun providePolicyService(retrofit: Retrofit) : FetchService {
        return retrofit.create(FetchService::class.java)
    }
    @Singleton
    @Provides
    fun provideAxaRemoteDataSource(fetchService: FetchService) = FetchRemoteDataSource(fetchService)

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: FetchRemoteDataSource) =
        FetchRepository(remoteDataSource)
}