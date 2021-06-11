package com.example.nytmostpopular.data.repositories.util

import com.example.nytmostpopular.data.local.models.Article
import com.example.nytmostpopular.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    fun getTrueArticleList(articlePeriod: Int = 1): Flow<Resource<List<Article>>>

}