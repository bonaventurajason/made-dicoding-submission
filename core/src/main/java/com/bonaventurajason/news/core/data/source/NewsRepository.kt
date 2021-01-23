package com.bonaventurajason.news.core.data.source

import com.bonaventurajason.news.core.data.source.local.LocalDataSource
import com.bonaventurajason.news.core.data.source.remote.RemoteDataSource
import com.bonaventurajason.news.core.data.source.remote.network.ApiResponse
import com.bonaventurajason.news.core.domain.model.News
import com.bonaventurajason.news.core.domain.repository.INewsRepository
import com.bonaventurajason.news.core.utils.AppExecutors
import com.bonaventurajason.news.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : INewsRepository {
    override fun getAllNews(): Flow<Resource<List<News>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.getAllNews().first()) {
                is ApiResponse.Success -> {
                    val newsList = DataMapper.mapResponsesToDomains(apiResponse.data)
                    emit(Resource.Success(newsList))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error<List<News>>("Empty", null))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<News>>(apiResponse.errorMessage))
                }
            }
        }

    }

    override fun searchNews(searchQuery: String): Flow<Resource<List<News>>> {
        return flow {
            emit(Resource.Loading())
            when (val apiResponse = remoteDataSource.searchNews(searchQuery).first()) {
                is ApiResponse.Success -> {
                    val newsList = DataMapper.mapResponsesToDomains(apiResponse.data)
                    emit(Resource.Success(newsList))
                }
                is ApiResponse.Empty -> {
                    emit(Resource.Error<List<News>>("Empty", null))
                }
                is ApiResponse.Error -> {
                    emit(Resource.Error<List<News>>(apiResponse.errorMessage))
                }
            }
        }
    }

    override fun insertNews(news: News) {
        val newsEntity = DataMapper.mapDomainToEntity(news)
        appExecutors.diskIO().execute { localDataSource.insertNews(newsEntity) }
    }

    override fun deleteNews(title: String) {
        appExecutors.diskIO().execute { localDataSource.deleteNews(title) }
    }

    override fun getAllFavouriteNews(): Flow<List<News>> {
        return localDataSource.getAllFavouriteNews().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun checkFavouriteNews(title: String): Flow<News> {
        return localDataSource.checkFavouriteNews(title).map { DataMapper.mapEntityToDomain(it) }
    }
}