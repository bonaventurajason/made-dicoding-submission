package com.bonaventurajason.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bonaventurajason.news.R
import com.bonaventurajason.news.core.domain.model.News
import com.bonaventurajason.news.databinding.FragmentDetailNewsBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DetailNewsFragment : Fragment(){

    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailNewsViewModel by viewModels()

    private val args: DetailNewsFragmentArgs by navArgs()

    private var isFavourite = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val news = args.news




        setWebView(news)

        checkIsNewsFavourite(news.title ?: "")

        clickFab(news)
    }

    private fun clickFab(news: News) {
        binding.fab.setOnClickListener {
            isFavourite = if(isFavourite){
                viewModel.deleteNews(news.title ?: "")
                Snackbar.make(it, getString(R.string.not_favourite_news), Snackbar.LENGTH_SHORT)
                    .show()
                binding.fab.setImageResource(R.drawable.ic_thumb_up)
                false
            } else {
                viewModel.insertNews(news)
                Snackbar.make(it, getString(R.string.favourite_news), Snackbar.LENGTH_SHORT)
                    .show()
                binding.fab.setImageResource(R.drawable.ic_thumb_down)
                true
            }
        }

    }

    private fun checkIsNewsFavourite(title: String) {
        viewModel.checkFavouriteNews(title).observe(viewLifecycleOwner, { news ->
            Timber.d("News  : $news")
            isFavourite = if(news.title != null){
                binding.fab.setImageResource(R.drawable.ic_thumb_down)
                true
            } else {
                binding.fab.setImageResource(R.drawable.ic_thumb_up)
                false
            }
        })

    }


    private fun setWebView(news : News) {
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(news.url ?: "")
        }
    }
}