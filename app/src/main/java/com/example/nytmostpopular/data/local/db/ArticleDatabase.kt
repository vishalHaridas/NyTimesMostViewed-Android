package com.example.nytmostpopular.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nytmostpopular.data.local.models.Article

@Database(
        entities = [Article::class],
        version = 1
)
abstract class ArticleDatabase : RoomDatabase(){

    abstract fun getArticleDao(): ArticleDao
}