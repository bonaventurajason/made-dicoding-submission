package com.bonaventurajason.news.di

import com.bonaventurajason.news.core.domain.usecase.NewsInteractor
import com.bonaventurajason.news.core.domain.usecase.NewsUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideNewsUseCase(newsInteractor: NewsInteractor) : NewsUseCase
}