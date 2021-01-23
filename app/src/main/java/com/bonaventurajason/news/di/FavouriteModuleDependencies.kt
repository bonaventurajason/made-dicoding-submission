package com.bonaventurajason.news.di

import com.bonaventurajason.news.core.domain.usecase.NewsUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface FavouriteModuleDependencies {

    fun newsUseCase(): NewsUseCase
}