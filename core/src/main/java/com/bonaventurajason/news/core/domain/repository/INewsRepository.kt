package com.bonaventurajason.news.core.domain.repository

import com.bonaventurajason.news.core.data.source.Resource
import com.bonaventurajason.news.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    fun getAllNews(): Flow<Resource<List<News>>>
    fun searchNews(searchQuery: String): Flow<Resource<List<News>>>

    fun insertNews(news: News)
    fun deleteNews(title: String)
    fun getAllFavouriteNews() : Flow<List<News>>
    fun checkFavouriteNews(title: String) : Flow<News>
}