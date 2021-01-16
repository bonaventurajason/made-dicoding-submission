package com.bonaventurajason.news.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bonaventurajason.news.core.domain.model.News
import com.bonaventurajason.news.core.domain.usecase.NewsUseCase

class DetailNewsViewModel @ViewModelInject constructor(private val newsUseCase: NewsUseCase) :
    ViewModel() {

    fun checkFavouriteNews(title: String) =
        newsUseCase.checkFavouriteNews(title).asLiveData()

    fun insertNews(news: News) = newsUseCase.insertNews(news)
    fun deleteNews(title: String) = newsUseCase.deleteNews(title)
}