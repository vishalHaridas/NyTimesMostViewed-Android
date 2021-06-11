package com.example.nytmostpopular.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.nytmostpopular.R
import kotlinx.android.synthetic.main.fragment_article_web.*


class ArticleWebFragment : Fragment(R.layout.fragment_article_web){

    private val args: ArticleWebFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadPage()
    }

    private fun loadPage() {
        webView.apply {
            webViewClient = WebViewClient()
            loadUrl(args.article.url)
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
    }
}