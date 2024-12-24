package com.example.myapplication.MVVM.PostModule.data.data_sources

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.MVVM.PostModule.data.models.Post
import com.example.myapplication.views.movies.data.Movie


@Dao
interface PostDAO {
    @Query("SELECT * FROM post_table")
    suspend fun getListPost(): List<Post>

    @Delete()
    suspend fun deletePost(post: Post) : Int

    @Insert
    suspend fun insertPost(post: Post):Long

    @Update
    suspend fun updatePost(post: Post):Int

    @Query("UPDATE post_table SET isLike = :isLike WHERE id = :id")
    suspend fun updateIsLike(id: Int, isLike: Boolean): Int

}