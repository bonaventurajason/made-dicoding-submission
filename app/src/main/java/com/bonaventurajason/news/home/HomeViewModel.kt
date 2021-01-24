package com.bonaventurajason.news.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import com.bonaventurajason.news.core.domain.usecase.NewsUseCase

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