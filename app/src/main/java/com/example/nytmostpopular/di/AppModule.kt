package com.example.nytmostpopular.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.nytmostpopular.R
import com.example.nytmostpopular.data.local.db.ArticleDao
import com.example.nytmostpopular.data.local.db.ArticleDatabase
import com.example.nytmostpopular.data.remote.api.ArticleAPI
import com.example.nytmostpopular.data.repositories.DefaultArticleRepository
import com.example.nytmostpopular.util.Constants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //RetroFit

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor
    = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient
    = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideArticleAPI(okHttpClient: OkHttpClient) : ArticleAPI
    = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(Constants.BASE_URL)
        .client(okHttpClient)
        .build()
        .create(ArticleAPI::class.java)


    //Glide

    @Singleton
    @Provides
    fun provideGlideInstance(@ApplicationContext context: Context)
    = Glide.with(context)
        .setDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_image)
        )

    //Room

    @Singleton
    @Provides
    fun provideArticleDao(database: ArticleDatabase) : ArticleDao = database.getArticleDao()

    @Singleton
    @Provides
    fun provideArticleDatabase( app: Application) : ArticleDatabase
        = Room.databaseBuilder(app, ArticleDatabase::class.java, Constants.DATABASE_NAME)
            .build()

    @Singleton
    @Provides
    fun providesDefaultArticleRepository(db: ArticleDatabase, api: ArticleAPI) : DefaultArticleRepository
        = DefaultArticleRepository(db, api)
}