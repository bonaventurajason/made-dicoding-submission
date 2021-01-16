package com.bonaventurajason.news.core.data.source.remote.network

import com.bonaventurajason.news.core.BuildConfig
import com.bonaventurajason.news.core.data.source.remote.response.ListNewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v2/top-headlines")
    suspend fun getListNews(
        @Query("country")
        countryCode: String = "id",
        @Query("apiKey")
        apiKey: String = BuildConfig.NEWS_TOKEN
    ): ListNewsResponse

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = BuildConfig.NEWS_TOKEN
    ) : ListNewsResponse

}