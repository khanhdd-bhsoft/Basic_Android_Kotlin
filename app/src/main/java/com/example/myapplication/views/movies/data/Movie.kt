package com.example.myapplication.views.movies.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class Movie(
 @PrimaryKey(autoGenerate = true) val id: Int = 0,
 @ColumnInfo(name = "title") val title: String,
 @ColumnInfo(name = "subtitle") val subtitle: String,
 @ColumnInfo(name = "rating") val rating: Double
)
