package com.example.nytmostpopular.ui

import androidx.lifecycle.*
import com.example.nytmostpopular.NytMostPopularApplication
import com.example.nytmostpopular.data.repositories.DefaultArticleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    repository: DefaultArticleRepository
): ViewModel(){

    var articles = repository
        .getTrueArticleList()
        .asLiveData()

}