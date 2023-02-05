package com.example.cinemasearch.presentation.fragments

//import com.example.cinemasearch.presentation.fragments.MainScreenFragment.idId.idMovie
import android.os.Bundle
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
import com.example.cinemasearch.presentation.adapters.MainScreenAdapter
import com.example.cinemasearch.presentation.view_model.MainScreenViewModel

class MainScreenFragment : Fragment(R.layout.main_screen) {
    private lateinit var binding: MainScreenBinding
    private val viewModel: MainScreenViewModel by viewModels()
    private lateinit var listAdapter: MainScreenAdapter
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initObservers()

        swipe.setOnRefreshListener {
            viewModel.onRefreshList()
            swipe.isRefreshing = false
        }

        binding.buttonSearchMovie.setOnClickListener {
            viewModel.onSearchClicked(binding.searchMovie.text.toString())
        }
    }


    private fun initObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
//        viewModel.isError.observe(viewLifecycleOwner) {
//            binding.emptyView.visibility = if (it) View.VISIBLE else View.GONE
//            binding.recyclerView.visibility = if (it) View.GONE else View.VISIBLE
//
//        }

        viewModel.listMovie.observe(viewLifecycleOwner) {
            listAdapter.setMovieList(it)
            listAdapter.setOnClickListener { film ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, DetailMovieFragment.getInstance(film.filmId))
                    .addToBackStack(MainScreenFragment().tag)
                    .commit()
            }
        }
    }


    private fun initRecyclerView() {
        listAdapter = MainScreenAdapter()
        binding.recyclerView.apply {
            adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val dividerItemDecoration = DividerItemDecoration(
                context,
                (layoutManager as LinearLayoutManager).orientation
            )
            addItemDecoration(dividerItemDecoration)
        }
    }
}
