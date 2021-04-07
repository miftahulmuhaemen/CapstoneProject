package com.example.capstoneproject.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstoneproject.core.BuildConfig
import com.example.capstoneproject.core.R
import com.example.capstoneproject.core.databinding.ItemsContentBinding
import com.example.capstoneproject.core.domain.model.Tv
import java.util.*

class TvAdapter : RecyclerView.Adapter<TvAdapter.ListViewHolder>() {

    private var listData = ArrayList<Tv>()
    var onItemClick: ((Tv) -> Unit)? = null

    fun setData(newListData: List<Tv>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_content, parent, false)
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemsContentBinding.bind(itemView)
        fun bind(data: Tv) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(BuildConfig.IMAGE_URL + data.posterPath)
                    .into(imgPoster)
                tvItemTitle.text = data.name
                tvItemDate.text = data.firstAirDate
                tvItemDescription.text = data.overview
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
                binding.root.setOnClickListener(null)
            }
        }
    }
}