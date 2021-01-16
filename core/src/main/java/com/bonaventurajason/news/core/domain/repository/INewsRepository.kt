package com.bonaventurajason.news.core.domain.repository

import com.bonaventurajason.news.core.data.source.Resource
import com.bonaventurajason.news.core.domain.model.News
import kotlinx.coroutines.flow.Flow

interface INewsRepository {

    suspend fun getAllNews(): Flow<Resource<List<News>>>
    suspend fun searchNews(searchQuery: String): Flow<Resource<List<News>>>
}