package com.bonaventurajason.news.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news")
data class NewsEntity(
    @ColumnInfo(name = "publishedAt")
    var publishedAt: String?,

    @ColumnInfo(name = "urlToImage")
    var urlToImage: String?,

    @ColumnInfo(name = "title")
    var title: String?,

    @ColumnInfo(name = "url")
    var url: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}