package com.example.nytmostpopular.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nytmostpopular.R
import com.example.nytmostpopular.data.util.Resource
import com.example.nytmostpopular.ui.ArticleViewModel
import com.example.nytmostpopular.ui.adapters.ArticleListItemAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_articles.*
import javax.inject.Inject

@AndroidEntryPoint
class ArticleListFragment @Inject constructor(
    val articleListItemAdapter: ArticleListItemAdapter,
): Fragment(R.layout.fragment_articles){

    private val viewModel: ArticleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleListItemAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }

            findNavController().navigate(
                    R.id.action_articleListFragment_to_articleDetailsFragment,
                    bundle
            )
        }

        subscribeToObservers()
        setupRecyclerView()

    }

    private fun subscribeToObservers(){
        viewModel.articles.observe(viewLifecycleOwner, { response ->
            when (response) {
                is Resource.Success -> {

                    progress_bar.visibility = View.GONE

                    response.data.let {
                        articleListItemAdapter.articleItems = it!!
                    }
                }
                is Resource.Error -> {

                    progress_bar.visibility = View.GONE

                    response.error?.message.let { message ->
                        Snackbar.make(requireActivity().rootLayout,  message ?: "No error message received!", Snackbar.LENGTH_LONG)
                                .show()
                        text_view_error.visibility = View.VISIBLE
                        text_view_error.text = message
                    }
                }
                is Resource.Loading -> {
                    progress_bar.visibility = View.VISIBLE
                }
            }
        })

    }

    private fun setupRecyclerView(){
        articles_recycler.apply {
            adapter = articleListItemAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

}