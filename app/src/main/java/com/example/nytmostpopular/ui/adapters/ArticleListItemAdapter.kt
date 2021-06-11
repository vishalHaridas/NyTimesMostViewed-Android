package com.example.nytmostpopular.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.nytmostpopular.R
import com.example.nytmostpopular.data.local.models.Article
import kotlinx.android.synthetic.main.item_article_list.view.*
import javax.inject.Inject

class ArticleListItemAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<ArticleListItemAdapter.ArticleViewHolder>(){

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffcallback = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffcallback)

    var articleItems: List<Article>
        get() = differ.currentList
        set(value) = differ.submitList(value)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_article_list,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articleItems[position]

        holder.itemView.apply {
            glide.load(article.mediaUrl).into(ivArticleImage)

            tvSource.text = article.byline
            tvTitle.text = article.title
            tvPublishedAt.text = article.published_date

            setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return articleItems.size
    }

    private var onItemClickListener: ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) { onItemClickListener = listener }


}