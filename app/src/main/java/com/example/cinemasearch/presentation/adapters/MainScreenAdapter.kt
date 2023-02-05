package com.example.cinemasearch.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinemasearch.data.DTOmodel.Film
import com.example.cinemasearch.databinding.ItemMovieBinding

class MainScreenAdapter  : RecyclerView.Adapter<MainScreenAdapter.ViewHolder>() {
    private var movies = mutableListOf<Film>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(movies[position]) }
        }
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            with(binding) {
                releaseDatesMovie.text = film.year
                nameMovie.text = film.nameRu

                Glide.with(itemView)
                    .load(film.posterUrlPreview)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageMovie)
            }
        }
    }

    fun setMovieList(films: List<Film>) {
        movies = films.toMutableList()
        notifyDataSetChanged()
    }

    private var onItemClickListener: ((Film) -> Unit)? = null

    fun setOnClickListener(listener: (Film) -> Unit) {
        onItemClickListener = listener
    }
}