package com.bonaventurajason.news.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bonaventurajason.news.core.data.source.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNews(newsEntity: NewsEntity)

    @Query("DELETE FROM news WHERE title = :title")
    fun deleteNews(title: String)

    @Query("SELECT * from news")
    fun getAllFavouriteNews(): Flow<List<NewsEntity>>

    @Query("SELECT * FROM news WHERE title = :title")
    fun checkFavouriteNews(title: String): Flow<NewsEntity>
}