package com.bonaventurajason.news.favourite

import android.content.Context
import com.bonaventurajason.news.di.FavouriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavouriteModuleDependencies::class])
interface FavouriteComponent {

    fun inject(fragment: FavouriteFragment)

    @Component.Builder
    interface Builder{
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favouriteModuleDependencies: FavouriteModuleDependencies): Builder
        fun build(): FavouriteComponent
    }
}