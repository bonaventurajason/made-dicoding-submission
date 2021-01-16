package com.bonaventurajason.news.core.utils

import com.bonaventurajason.news.core.data.source.local.entity.NewsEntity
import com.bonaventurajason.news.core.data.source.remote.response.NewsResponse
import com.bonaventurajason.news.core.domain.model.News

object DataMapper {
    fun mapResponsesToEntities(input: List<NewsResponse>): List<NewsEntity>{
        val newsList = ArrayList<NewsEntity>()
        input.map {
            val news = NewsEntity(
                publishedAt = it.publishedAt ?: "",
                urlToImage = it.urlToImage ?: "",
                title = it.title ?: "",
                url = it.url ?: ""
            )
            newsList.add(news)
        }
        return newsList
    }

    fun mapEntitiesToDomain(input: List<NewsEntity>): List<News> =
        input.map {
            News(
                publishedAt = it.publishedAt,
                urlToImage = it.urlToImage,
                title = it.title,
                url = it.url
            )
        }

    fun mapDomainToEntity(input: News) = NewsEntity(
        publishedAt = input.publishedAt,
        urlToImage = input.urlToImage,
        url = input.url,
        title = input.title
    )

    fun mapResponsesToDomains(input: List<NewsResponse>): List<News>{
        val newsList = ArrayList<News>()
        input.map {
            val news = News(
                publishedAt = it.publishedAt ?: "",
                urlToImage = it.urlToImage ?: "",
                title = it.title ?: "",
                url = it.url ?: ""
            )
            newsList.add(news)
        }
        return newsList
    }
}