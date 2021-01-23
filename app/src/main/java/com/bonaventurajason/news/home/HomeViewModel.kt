package com.bonaventurajason.news.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bonaventurajason.news.core.data.source.Resource
import com.bonaventurajason.news.core.domain.model.News
import com.bonaventurajason.news.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val newsUseCase: NewsUseCase) :
    ViewModel() {

    private val currentQuery = MutableLiveData<String>()

    val headlineNews = newsUseCase.getAllNews().asLiveData()

    val news = currentQuery.switchMap { queryString ->
        newsUseCase.searchNews(queryString).asLiveData()
    }

    fun searchNews(query: String){
        currentQuery.value = query
    }



}