package com.bonaventurajason.news.core.domain.usecase

import com.bonaventurajason.news.core.data.source.Resource
import com.bonaventurajason.news.core.domain.model.News
import com.bonaventurajason.news.core.domain.repository.INewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(private val newsRepository: INewsRepository) :
    NewsUseCase {
    override suspend fun getAllNews() = newsRepository.getAllNews()
    override suspend fun searchNews(searchQuery: String) = newsRepository.searchNews(searchQuery)
}