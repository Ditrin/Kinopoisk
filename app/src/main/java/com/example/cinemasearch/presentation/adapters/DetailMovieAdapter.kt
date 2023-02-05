package com.example.cinemasearch.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cinemasearch.databinding.ItemSingleBinding

class DetailMovieAdapter : RecyclerView.Adapter<DetailMovieAdapter.ViewHolder>() {
    private var singleMovie: List<String> = emptyList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailMovieAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemSingleBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailMovieAdapter.ViewHolder, position: Int) {
        holder.bind(singleMovie[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(singleMovie[position]) }
        }
    }

    override fun getItemCount(): Int = singleMovie.size

    inner class ViewHolder(private val binding: ItemSingleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(singleMovie: String) {
            with(binding) {
                single.text = singleMovie
            }
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null
}