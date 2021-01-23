package com.bonaventurajason.news.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bonaventurajason.news.core.domain.usecase.NewsUseCase

class FavouriteViewModel (newsUseCase: NewsUseCase) : ViewModel(){

    val favouriteNews = newsUseCase.getAllFavouriteNews().asLiveData()
}