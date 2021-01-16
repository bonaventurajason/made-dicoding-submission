package com.bonaventurajason.news.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bonaventurajason.news.core.domain.model.News
import com.bonaventurajason.news.databinding.FragmentDetailNewsBinding

class DetailNewsFragment : Fragment(){

    private var _binding: FragmentDetailNewsBinding? = null
    private val binding get() = _binding!!


    private val args: DetailNewsFragmentArgs by navArgs()

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
    }

    private fun setWebView(news : News) {
        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(news.url)
        }
    }
}