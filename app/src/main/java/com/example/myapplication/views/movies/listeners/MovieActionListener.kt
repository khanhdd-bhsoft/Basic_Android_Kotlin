package com.example.myapplication.views.movies.listeners

import com.example.myapplication.views.movies.data.Movie

interface MovieActionListener {
    fun editMovie(movie: Movie):Int
    fun deleteMovie(movie: Movie)
}