package com.bonaventurajason.news.core.di

import com.bonaventurajason.news.core.data.source.NewsRepository
import com.bonaventurajason.news.core.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent


@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(newsRepository: NewsRepository) : INewsRepository

}