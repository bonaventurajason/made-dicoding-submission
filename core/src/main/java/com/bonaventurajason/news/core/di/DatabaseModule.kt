package com.bonaventurajason.news.core.di

import android.content.Context
import androidx.room.Room
import com.bonaventurajason.news.core.data.source.local.room.NewsDao
import com.bonaventurajason.news.core.data.source.local.room.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : NewsDatabase =
        Room.databaseBuilder(
            context,
            NewsDatabase::class.java, "NewsDatabase.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideNewsDao(database: NewsDatabase) : NewsDao = database.newsDao()

}