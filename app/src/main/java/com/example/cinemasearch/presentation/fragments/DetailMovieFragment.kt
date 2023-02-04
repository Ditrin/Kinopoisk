package com.example.cinemasearch.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.cinemasearch.R
import com.example.cinemasearch.databinding.FragmentDetailMovieBinding
import com.example.cinemasearch.presentation.adapters.DetailMovieAdapter
import com.example.cinemasearch.presentation.fragments.MainScreenFragment.idId.idMovie
import com.example.cinemasearch.presentation.view_model.DetailMovieViewModel

class DetailMovieFragment: Fragment(R.layout.fragment_detail_movie) {
    private lateinit var binding: FragmentDetailMovieBinding
    private val viewModel: DetailMovieViewModel by viewModels()
    private lateinit var listAdapter: DetailMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        binding.appBarInfo.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = DetailMovieAdapter()
        viewModel.getSingleCharacter(idMovie)
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE

            } else
                binding.progressBar.visibility = View.GONE
        }

        viewModel.singleCharacter.observe(viewLifecycleOwner) {
            with(binding) {
                genreMovie.text = it.genres.first().genre
                nameMovie.text = it.nameRu
                descriptionMovie.text = it.description
                countriesMovie.text = it.countries.first().country

                Glide.with(this@DetailMovieFragment)
                    .load(it.posterUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageMovie)

            }
        }
    }
}