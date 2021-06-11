package com.example.nytmostpopular.data.remote

import com.example.nytmostpopular.data.local.models.Article
import com.example.nytmostpopular.data.remote.models.NetworkArticle
import com.example.nytmostpopular.data.util.EntityMapper


class ArticleNetworkMapper : EntityMapper<NetworkArticle, Article> {
    override fun mapFromEntityList(entityList: List<NetworkArticle>): List<Article> =
        entityList.map { mapFromEntity(it) }

    override fun mapFromEntity(entity: NetworkArticle): Article =
        Article(entity)

}