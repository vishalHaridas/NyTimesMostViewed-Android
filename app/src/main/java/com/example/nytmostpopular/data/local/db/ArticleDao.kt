package com.example.nytmostpopular.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.nytmostpopular.data.local.models.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDao {

    @Query("DELETE FROM article")
    suspend fun deleteAllArticles()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAllArticles(articleList : List<Article>)

    @Query("SELECT * FROM article")
    fun getAllArticlesFromDb(): Flow<List<Article>>

}