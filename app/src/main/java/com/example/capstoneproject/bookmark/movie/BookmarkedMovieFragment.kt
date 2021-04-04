package com.example.capstoneproject.bookmark.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.core.data.Resource
import com.example.capstoneproject.core.ui.MovieAdapter
import com.example.capstoneproject.core.ui.base.BaseBottomTabFragment
import com.example.capstoneproject.databinding.FragmentContentBinding
import com.example.capstoneproject.movie.MovieFragmentDirections
import com.example.capstoneproject.movie.MovieViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkedMovieFragment : BaseBottomTabFragment() {

    private var _binding : FragmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapter: MovieAdapter
    private val movieViewModel by viewModel<BookmarkedMovieViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        movieAdapter.onItemClick = {
            navigateWithAction(BookmarkedMovieFragmentDirections.actionBookmarkedMovieFragmentToDetailFragment(it,null))
        }
        with(binding.recylerView){
            layoutManager = LinearLayoutManager(context)
            adapter = movieAdapter
        }

        movieViewModel.movies.observe(viewLifecycleOwner, { movie ->
            movieAdapter.setData(movie)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}