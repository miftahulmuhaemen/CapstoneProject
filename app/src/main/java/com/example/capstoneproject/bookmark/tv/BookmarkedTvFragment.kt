package com.example.capstoneproject.bookmark.tv

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstoneproject.R
import com.example.capstoneproject.core.data.Resource
import com.example.capstoneproject.core.ui.TvAdapter
import com.example.capstoneproject.core.ui.base.BaseBottomTabFragment
import com.example.capstoneproject.databinding.FragmentContentBinding
import com.example.capstoneproject.tv.TvFragmentDirections
import com.example.capstoneproject.tv.TvViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BookmarkedTvFragment : BaseBottomTabFragment() {

    private var _binding : FragmentContentBinding? = null
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
            navigateWithAction(BookmarkedTvFragmentDirections.actionBookmarkedTvFragmentToDetailFragment(null, it))
        }
        with(binding.recylerView){
            layoutManager = LinearLayoutManager(context)
            adapter = tvAdapter
        }
        tvViewModel.tvs.observe(viewLifecycleOwner, { tvs ->
            tvAdapter.setData(tvs)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}