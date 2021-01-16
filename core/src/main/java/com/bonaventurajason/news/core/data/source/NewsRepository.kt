package com.bonaventurajason.news.core.data.source

import com.bonaventurajason.news.core.data.source.remote.RemoteDataSource
import com.bonaventurajason.news.core.data.source.remote.network.ApiResponse
import com.bonaventurajason.news.core.domain.model.News
import com.bonaventurajason.news.core.domain.repository.INewsRepository
import com.bonaventurajason.news.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : INewsRepository {
    override suspend fun getAllNews(): Flow<Resource<List<News>>> {
        return flow {
            emit(Resource.Loading())
            when(val apiResponse = remoteDataSource.getAllNews().first()){
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

    override suspend fun searchNews(searchQuery: String): Flow<Resource<List<News>>> {
        return flow {
            emit(Resource.Loading())
            when(val apiResponse = remoteDataSource.searchNews(searchQuery).first()){
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
}