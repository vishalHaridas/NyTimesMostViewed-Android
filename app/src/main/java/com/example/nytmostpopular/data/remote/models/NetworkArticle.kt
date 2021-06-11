package com.example.nytmostpopular.data.remote.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

data class NetworkArticle (
    val id: Long,
    val abstract: String,
    val url: String,
    val title: String,
    val byline: String,
    val published_date: String,
    val media: List<NetworkMedia>?,
){

}