package com.example.capstoneproject.bookmark.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.capstoneproject.BuildConfig
import com.example.capstoneproject.R
import com.example.capstoneproject.core.domain.model.Movie
import com.example.capstoneproject.core.domain.model.Tv
import com.example.capstoneproject.databinding.FragmentDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailBookmarkFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val detailBookmarkViewModel: DetailBookmarkViewModel by viewModel()
    private val args: DetailBookmarkFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val detailMovie = args.movie
        val detailTv = args.tv

        if (detailMovie != null) {
            showDetailMovie(detailMovie)
        } else {
            showDetailTv(detailTv)
        }
    }

    private fun showDetailMovie(detailMovie: Movie?) {
        detailMovie?.let { data ->
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL + data.posterPath)
                .into(binding.imgPoster)
            binding.tvItemTitle.text = data.title
            binding.tvItemDate.text = data.releaseDate
            binding.tvItemDescription.text = data.overview

            var statusBookmark = detailMovie.bookmarked
            setStatusBookmark(statusBookmark)
            binding.fab.setOnClickListener {
                statusBookmark = !statusBookmark
                detailBookmarkViewModel.setBookmarkMovie(detailMovie, statusBookmark)
                setStatusBookmark(statusBookmark)
            }
        }
    }

    private fun showDetailTv(detailTv: Tv?) {
        detailTv?.let { data ->
            Glide.with(this)
                .load(BuildConfig.IMAGE_URL + data.posterPath)
                .into(binding.imgPoster)
            binding.tvItemTitle.text = data.name
            binding.tvItemDate.text = data.firstAirDate
            binding.tvItemDescription.text = data.overview

            var statusBookmark = detailTv.bookmarked
            setStatusBookmark(statusBookmark)
            binding.fab.setOnClickListener {
                statusBookmark = !statusBookmark
                detailBookmarkViewModel.setBookmarkTv(detailTv, statusBookmark)
                setStatusBookmark(statusBookmark)
            }
        }
    }

    private fun setStatusBookmark(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}