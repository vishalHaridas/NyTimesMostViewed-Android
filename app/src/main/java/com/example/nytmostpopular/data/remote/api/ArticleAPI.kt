package com.example.nytmostpopular.data.remote.api

import com.example.nytmostpopular.data.remote.models.ArticleResponse
import com.example.nytmostpopular.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface ArticleAPI {

    @GET("/svc/mostpopular/v2/viewed/{period}.json")
    suspend fun getMostViewed(
        @Path("period") period: String = "1",
        @Query("api-key") key: String = API_KEY
    ): ArticleResponse
}