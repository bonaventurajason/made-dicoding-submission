package com.bonaventurajason.news.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.bonaventurajason.news.core.data.source.Resource
import com.bonaventurajason.news.core.domain.model.News
import com.bonaventurajason.news.core.domain.usecase.NewsUseCase
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val newsUseCase: NewsUseCase) :
    ViewModel() {

    fun getNews(): LiveData<Resource<List<News>>> {
        lateinit var news: LiveData<Resource<List<News>>>
        viewModelScope.launch {
            news = newsUseCase.getAllNews().asLiveData()
        }
        return news
    }

    fun searchNews(searchQuery: String): LiveData<Resource<List<News>>> {
        lateinit var searchNews: LiveData<Resource<List<News>>>
        viewModelScope.launch {
            searchNews = newsUseCase.searchNews(searchQuery).asLiveData()
        }
        return searchNews
    }


}