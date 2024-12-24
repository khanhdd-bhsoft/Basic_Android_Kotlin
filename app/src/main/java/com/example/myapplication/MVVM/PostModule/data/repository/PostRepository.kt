package com.example.myapplication.MVVM.PostModule.data.repository

import android.content.Context
import com.example.myapplication.MVVM.PostModule.data.data_sources.MyRoomDB
import com.example.myapplication.MVVM.PostModule.data.models.Post

class PostRepository(context: Context) {
    private val postDatabase: MyRoomDB = MyRoomDB.getDatabase(context)

    suspend fun getAllPost() : List<Post>
    {
        return  postDatabase.postDAO().getListPost()
    }

    suspend fun deletePost(post: Post) : Int
    {
        return postDatabase.postDAO().deletePost(post)
    }

    suspend fun updatePost(post: Post) : Int
    {
        return postDatabase.postDAO().updatePost(post)
    }

    suspend fun insertPost(post: Post) : Long
    {
        return postDatabase.postDAO().insertPost(post)
    }

    suspend fun updateIsLike(id : Int,isLike : Boolean) : Int
    {
        return postDatabase.postDAO().updateIsLike(id,isLike)
    }
}