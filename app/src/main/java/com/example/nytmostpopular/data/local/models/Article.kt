package com.example.nytmostpopular.data.local.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nytmostpopular.data.remote.models.NetworkArticle
import java.io.Serializable

@Entity( tableName = "article" )
data class Article(
        @PrimaryKey(autoGenerate = false)
        var id: Long,
        var url: String,
        var title: String,
        var abstract_test: String,
        var byline: String,                 //Author
        var mediaUrl: String,
        var published_date: String,
): Serializable {
        constructor(networkArticle: NetworkArticle) : this(
                networkArticle.id,
                networkArticle.url,
                networkArticle.title,
                networkArticle.abstract,
                networkArticle.byline,
                //returns the URL for the highest quality media (index 2)
                if (networkArticle.media.isNullOrEmpty()) "" else networkArticle.media[0].media_metadata[2].url,
                networkArticle.published_date
        )
}