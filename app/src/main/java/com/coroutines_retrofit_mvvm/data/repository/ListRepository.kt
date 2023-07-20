package com.coroutines_retrofit_mvvm.data.repository

import com.coroutines_retrofit_mvvm.data.api.RetrofitService
import com.coroutines_retrofit_mvvm.data.model.Movie
import com.coroutines_retrofit_mvvm.data.response.NetworkState

class ListRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun getAllMovies() : NetworkState<List<Movie>> {
        val response = retrofitService.getAllMovies()
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkState.Success(responseBody)
            } else {
                NetworkState.Error(response)
            }
        } else {
            NetworkState.Error(response)
        }
    }

}