package com.bonaventurajason.news.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bonaventurajason.news.core.ui.NewsAdapter
import com.bonaventurajason.news.di.FavouriteModuleDependencies
import com.bonaventurajason.news.favourite.databinding.FragmentFavouriteBinding
import dagger.hilt.android.EntryPointAccessors
import timber.log.Timber
import javax.inject.Inject

class FavouriteFragment : Fragment() {
    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: FavouriteViewModel by viewModels{
        factory
    }

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!



    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavouriteComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireActivity().application,
                    FavouriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(activity != null){

            newsAdapter = NewsAdapter()
            newsAdapter.setOnItemClickListener {
                findNavController().navigate(FavouriteFragmentDirections.actionFavouriteFragmentToDetailNewsFragment(it))
            }

            viewModel.favouriteNews.observe(viewLifecycleOwner, { news ->
                if(news != null){
                    Timber.d("News $news")
                    newsAdapter.differ.submitList(news)
                }
                binding.viewEmpty.root.visibility = if (news.isNotEmpty()) View.GONE else View.VISIBLE

            })

            with(binding.recyclerView) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = newsAdapter
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}