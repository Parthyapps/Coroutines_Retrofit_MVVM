package com.coroutines_retrofit_mvvm.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.coroutines_retrofit_mvvm.data.api.RetrofitService
import com.coroutines_retrofit_mvvm.data.repository.ListRepository
import com.coroutines_retrofit_mvvm.databinding.ActivityListBinding
import com.coroutines_retrofit_mvvm.ui.adapter.MovieListAdapter
import com.coroutines_retrofit_mvvm.ui.viewModel.MovieListPageViewModel
import com.coroutines_retrofit_mvvm.ui.viewModel.MyViewModelFactory

class MovieListPageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding
    private lateinit var userAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchUsers()
    }

    private fun setupRecyclerView() {
        userAdapter = MovieListAdapter(emptyList())
        binding.recyclerView.adapter = userAdapter
    }

    private fun fetchUsers() {

        val retrofit = RetrofitService.getInstance()
        val repository = ListRepository(retrofit)

        val viewModel = ViewModelProvider(this, MyViewModelFactory(repository))[MovieListPageViewModel::class.java]
        viewModel.movieList.observe(this) { users ->
            userAdapter = MovieListAdapter(users)
            binding.recyclerView.adapter = userAdapter
        }
        viewModel.getAllMovies()
    }
}