package com.bonaventurajason.news.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun convertDate(dateString : String) : String{

        val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        val date = inputFormatter.parse(dateString) as Date
        return outputFormatter.format(date)
    }
}