package com.bonaventurajason.news.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class News(
    val publishedAt: String,
    val urlToImage: String,
    val title: String,
    val url: String
) : Parcelable