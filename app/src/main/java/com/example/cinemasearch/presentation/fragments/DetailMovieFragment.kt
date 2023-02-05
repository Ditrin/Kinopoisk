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
import com.example.cinemasearch.data.model.CountryX
import com.example.cinemasearch.data.model.GenreX
import com.example.cinemasearch.databinding.FragmentDetailMovieBinding
import com.example.cinemasearch.presentation.view_model.DetailMovieViewModel

class DetailMovieFragment: Fragment(R.layout.fragment_detail_movie) {

companion object {
    private const val ARG_ID: String = "ARG_ID"

    fun getInstance(id: Int): Fragment {
        return DetailMovieFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
            }
        }
    }
}

    private lateinit var binding: FragmentDetailMovieBinding
    private val viewModel: DetailMovieViewModel by viewModels()

    private val movieId: Int by lazy{arguments?.getInt(ARG_ID) ?: error("Missing id")}

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

        viewModel.onInitScreen(movieId)
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.progressBar.visibility = View.VISIBLE

            } else
                binding.progressBar.visibility = View.GONE
        }

        viewModel.singleMovie.observe(viewLifecycleOwner) {
            with(binding) {
                genreMovie.text = it.genres.map(GenreX::genre).joinToString()
                nameMovie.text = it.nameRu
                descriptionMovie.text = it.description
                countriesMovie.text = it.countries.map(CountryX::country).joinToString()

                Glide.with(this@DetailMovieFragment)
                    .load(it.posterUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageMovie)
            }
        }
    }
}