package com.example.myapplication.views.movies.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface MovieDAO {

    @Query("SELECT * FROM movie_table")
    fun getListMovie(): List<Movie>

    @Query("SELECT * FROM movie_table WHERE title LIKE '%' || :name || '%'")
    fun searchMoviesByName(name: String) : List<Movie>

    @Delete
    fun delete(user: Movie)

    @Insert
    fun insertMovie(movie: Movie):Long

    @Update
    fun updateMovie(movie: Movie):Int
}