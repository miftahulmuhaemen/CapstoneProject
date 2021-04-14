package com.example.capstoneproject.bookmark.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.core.ui.TvAdapter
import com.example.capstoneproject.core.ui.base.BaseBottomTabFragment
import com.example.capstoneproject.databinding.FragmentContentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkedTvFragment : BaseBottomTabFragment() {

    private var _binding: FragmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvAdapter: TvAdapter
    private val tvViewModel by viewModel<BookmarkedTvViewModel>()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvAdapter = TvAdapter()
        tvAdapter.onItemClick = {
            navigateWithAction(
                    BookmarkedTvFragmentDirections.actionBookmarkedTvFragmentToDetailBookmarkFragment(
                            null,
                            it
                    )
            )
        }
        with(binding.recylerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = tvAdapter
        }
        tvViewModel.tvs.observe(viewLifecycleOwner, { tvs ->
            binding.emptyAnimation.visibility = if (tvs.isNullOrEmpty()) View.VISIBLE else View.GONE
            tvAdapter.setData(tvs)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recylerView.adapter = null
        _binding = null
    }

}