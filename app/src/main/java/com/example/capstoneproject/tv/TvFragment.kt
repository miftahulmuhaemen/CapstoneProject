package com.example.capstoneproject.tv

import android.os.Bundle
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
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvFragment : BaseBottomTabFragment() {

    private var _binding : FragmentContentBinding? = null
    private val binding get() = _binding!!
    private lateinit var tvAdapter: TvAdapter
    private val tvViewModel by viewModel<TvViewModel>()

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
            navigateWithAction(TvFragmentDirections.actionTvFragmentToDetailFragment(null,it))
        }
        with(binding.recylerView){
            layoutManager = LinearLayoutManager(context)
            adapter = tvAdapter
        }

        tvViewModel.tvs.observe(viewLifecycleOwner, { tv ->
            if (tv != null) {
                when (tv) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        tvAdapter.setData(tv.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(context, getString(R.string.something_wrong), Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}