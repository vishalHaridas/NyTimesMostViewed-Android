package com.example.nytmostpopular.data.remote.models

data class ArticleResponse(
    val copyright: String,
    val num_results: Int,
    val results: List<NetworkArticle>,
    val status: String
)