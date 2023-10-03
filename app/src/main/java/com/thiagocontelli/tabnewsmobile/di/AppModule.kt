package com.thiagocontelli.tabnewsmobile.di

import com.thiagocontelli.tabnewsmobile.data.TabNewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideTabNewsApi(): TabNewsApi {
        return Retrofit.Builder().baseUrl("https://www.tabnews.com.br/api/v1/")
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(TabNewsApi::class.java)
    }
}