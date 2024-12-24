package com.example.myapplication.views.movies.data

import android.content.Context

class MovieRepository(context: Context) {
    private val roomDB : RoomDB = RoomDB.getDatabase(context)
    fun getAllMovie(): List<Movie>
    {
        return roomDB.movieDao().getListMovie()
    }
    fun searchMoviesByName(name: String): List<Movie>
    {
        return roomDB.movieDao().searchMoviesByName(name)
    }
    fun insertMovie(movie: Movie):Long {
        return roomDB.movieDao().insertMovie(movie)
    }
    fun deleteMovie(movie: Movie) {
        return roomDB.movieDao().delete(movie);
    }
    fun updateMovie(movie: Movie):Int
    {
        return roomDB.movieDao().updateMovie(movie)
    }
}