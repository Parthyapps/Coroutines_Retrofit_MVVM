package com.coroutines_retrofit_mvvm.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.coroutines_retrofit_mvvm.data.repository.ListRepository

class MyViewModelFactory constructor(private val repository: ListRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieListPageViewModel::class.java)) {
            MovieListPageViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}