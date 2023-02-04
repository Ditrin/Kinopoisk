package com.example.cinemasearch.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.cinemasearch.data.DTOmodel.Film
import com.example.cinemasearch.databinding.ItemMovieBinding

class MainScreenAdapter  : RecyclerView.Adapter<MainScreenAdapter.ViewHolder>() {
    private var characters = mutableListOf<Film>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characters[position])
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(characters[position]) }
        }
    }

    override fun getItemCount(): Int = characters.size

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Film) {
            with(binding) {
                releaseDatesMovie.text = character.year
                nameMovie.text = character.nameRu

                Glide.with(itemView)
                    .load(character.posterUrlPreview)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageMovie)
            }
        }
    }

    fun setCharacterList(character: List<Film>) {
        characters = character.toMutableList()
        notifyDataSetChanged()
    }

    private var onItemClickListener: ((Film) -> Unit)? = null

    fun setOnClickListener(listener: (Film) -> Unit) {
        onItemClickListener = listener
    }
}