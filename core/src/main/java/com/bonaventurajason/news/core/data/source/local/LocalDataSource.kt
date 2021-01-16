package com.bonaventurajason.news.core.data.source.local

import com.bonaventurajason.news.core.data.source.local.entity.NewsEntity
import com.bonaventurajason.news.core.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val newsDao: NewsDao) {

    fun insertNews(newsEntity: NewsEntity) = newsDao.insertNews(newsEntity)

    fun deleteNews(title: String) = newsDao.deleteNews(title)

    fun getAllFavouriteNews(): Flow<List<NewsEntity>> = newsDao.getAllFavouriteNews()

    fun checkFavouriteNews(title: String): Flow<NewsEntity> = newsDao.checkFavouriteNews(title)
}