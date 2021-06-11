package com.example.nytmostpopular.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.example.nytmostpopular.ui.adapters.ArticleListItemAdapter
import com.example.nytmostpopular.ui.fragments.ArticleListFragment
import javax.inject.Inject

class ArticleFragmentFactory @Inject constructor(
        private val articleListItemAdapter: ArticleListItemAdapter
): FragmentFactory(){

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ArticleListFragment::class.java.name -> ArticleListFragment(articleListItemAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}