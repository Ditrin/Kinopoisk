package com.example.cinemasearch.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.cinemasearch.R
import com.example.cinemasearch.databinding.MainScreenBinding
import com.example.cinemasearch.presentation.adapters.CinemaSearchAdapter
import com.example.cinemasearch.presentation.fragments.MainScreenFragment.idId.idMovie
import com.example.cinemasearch.presentation.view_model.MainScreenViewModel

class MainScreenFragment: Fragment(R.layout.main_screen){
    private lateinit var binding: MainScreenBinding
    private val viewModel: MainScreenViewModel by viewModels()
    private lateinit var listAdapter: CinemaSearchAdapter
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainScreenBinding.inflate(inflater, container, false)
        swipe = binding.swipeRefresh
        return binding.root
    }

    object idId{
        var idMovie:Int = 0
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listAdapter = CinemaSearchAdapter()
        viewModel.getMoviesList()
        viewModel.isLoading.observe(viewLifecycleOwner){
            if(it){
                binding.progressBar.visibility = View.VISIBLE
            }
            else
                binding.progressBar.visibility = View.GONE
        }

        viewModel.listMovie.observe(viewLifecycleOwner){
            binding.recyclerView.apply {
                adapter = listAdapter
                layoutManager = LinearLayoutManager(requireContext())
                val dividerItemDecoration = DividerItemDecoration(
                    context,
                    (layoutManager as LinearLayoutManager).orientation
                )
                addItemDecoration(dividerItemDecoration)
            }
            listAdapter.setCharacterList(it)

            listAdapter.setOnClickListener {film->
                parentFragmentManager.beginTransaction().apply  {
                    idMovie = film.filmId
                    replace(R.id.fragmentContainer, DetailMovieFragment())
                    addToBackStack(MainScreenFragment().tag)
                    commit()
                }
            }
        }

        swipe.setOnRefreshListener {
            viewModel.refreshList()
            swipe.isRefreshing = false
        }

        binding.buttonSearchMovie.setOnClickListener{
            Log.e("Main", "size.toString()")

            viewModel.saveText(binding.searchMovie.text.toString())
            viewModel.searchText.observe(viewLifecycleOwner){keyword->
                viewModel.getSearchList(keyword)
            }
        }
    }
}