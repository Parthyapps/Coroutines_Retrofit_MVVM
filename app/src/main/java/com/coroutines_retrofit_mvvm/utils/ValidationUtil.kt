package com.coroutines_retrofit_mvvm.utils

import com.coroutines_retrofit_mvvm.data.model.Movie

object ValidationUtil {

    fun validateMovie(movie: Movie) : Boolean {
        if (movie.name.isNotEmpty() && movie.category.isNotEmpty()) {
            return true
        }
        return false
    }
}