package com.bonaventurajason.news.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bonaventurajason.news.core.R
import com.bonaventurajason.news.core.databinding.ItemNewsBinding
import com.bonaventurajason.news.core.domain.model.News
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>(){

     inner class ViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(news : News){
            with(binding){
                Glide.with(itemView.context)
                    .load(news.urlToImage)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                            .centerCrop()
                    ).into(photo)

                title.text = news.title
                date.text = news.publishedAt
                binding.root.setOnClickListener {
                    onItemClickListener?.let {
                        it(news)
                    }
                }

            }
        }


    }

    private val differCallback = object : DiffUtil.ItemCallback<News>(){
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = differ.currentList[position]
        if(news != null){
            holder.bind(news)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onItemClickListener: ((News) -> Unit)? = null

    fun setOnItemClickListener(listener: (News) -> Unit){
        onItemClickListener = listener
    }




}