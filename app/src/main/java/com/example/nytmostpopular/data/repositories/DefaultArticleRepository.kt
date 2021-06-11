package com.example.nytmostpopular.data.repositories

import android.util.Log
import androidx.room.withTransaction
import com.example.nytmostpopular.data.local.db.ArticleDatabase
import com.example.nytmostpopular.data.local.models.Article
import com.example.nytmostpopular.data.remote.ArticleNetworkMapper
import com.example.nytmostpopular.data.remote.api.ArticleAPI
import com.example.nytmostpopular.data.remote.models.ArticleResponse
import com.example.nytmostpopular.data.repositories.util.ArticleRepository
import com.example.nytmostpopular.data.util.networkBoundResource
import retrofit2.Response
import java.time.Period
import javax.inject.Inject

class DefaultArticleRepository @Inject constructor(
        private val articleDb: ArticleDatabase,
        private val articleAPI: ArticleAPI
) : ArticleRepository {

    private val articleDao = articleDb.getArticleDao()


    override fun getTrueArticleList(articlePeriod: Int) = networkBoundResource(
        query = {
            articleDao.getAllArticlesFromDb()
        },
        fetch = {
            val result = articleAPI.getMostViewed()
            handleArticleResponse(result)
        },
        saveFetchResult = { article ->
            articleDb.withTransaction {
                articleDao.deleteAllArticles()
                articleDao.upsertAllArticles(article)
            }
        }
    )

    private fun handleArticleResponse(response: ArticleResponse): List<Article> =
        ArticleNetworkMapper()
            .mapFromEntityList(response.results)
}