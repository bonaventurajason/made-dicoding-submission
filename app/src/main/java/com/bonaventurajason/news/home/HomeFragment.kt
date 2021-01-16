package com.bonaventurajason.news.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.LinearLayoutManager
import com.bonaventurajason.news.R
import com.bonaventurajason.news.core.data.source.Resource
import com.bonaventurajason.news.core.ui.NewsAdapter
import com.bonaventurajason.news.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            newsAdapter = NewsAdapter()

            newsAdapter.setOnItemClickListener {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailNewsFragment(
                        it
                    )
                )
            }

            homeViewModel.getNews().observe(viewLifecycleOwner, { news ->
                if (news != null) {
                    when (news) {
                        is Resource.Loading -> showProgressBar()
                        is Resource.Success -> {
                            hideProgressBar()
                            newsAdapter.differ.submitList(news.data)
                        }
                        is Resource.Error -> {
                            hideProgressBar()
                            Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })

            with(binding.recyclerView) {
                layoutManager = LinearLayoutManager(context)
                adapter = newsAdapter
            }

            searchUser()
        }
    }

    private fun searchUser() {
        binding.searchNews.apply {
            queryHint = context.getString(R.string.search_hint)
            isIconified = false
            isFocusable = false
            isFocusableInTouchMode = true
            clearFocus()
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        homeViewModel.searchNews(query).observe(viewLifecycleOwner, { news ->
                            if (news != null) {
                                when (news) {
                                    is Resource.Loading -> showProgressBar()
                                    is Resource.Success -> {
                                        hideProgressBar()
                                        newsAdapter.differ.submitList(news.data)
                                    }
                                    is Resource.Error -> {
                                        hideProgressBar()
                                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG)
                                            .show()
                                    }
                                }
                            }
                        })
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }

            })
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}