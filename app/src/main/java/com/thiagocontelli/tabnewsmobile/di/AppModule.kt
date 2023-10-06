package com.thiagocontelli.tabnewsmobile.di

import android.content.Context
import com.thiagocontelli.tabnewsmobile.data.TabNewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.noties.markwon.Markwon
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.ext.tasklist.TaskListPlugin
import io.noties.markwon.image.glide.GlideImagesPlugin
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

    @Provides
    fun provideMarkwon(@ApplicationContext context: Context): Markwon {
        return Markwon.builder(context).usePlugin(GlideImagesPlugin.create(context))
            .usePlugin(TablePlugin.create(context)).usePlugin(TaskListPlugin.create(context))
            .build()
    }
}