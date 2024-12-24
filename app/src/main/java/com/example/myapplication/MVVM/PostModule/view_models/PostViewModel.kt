package com.example.myapplication.MVVM.PostModule.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.MVVM.PostModule.data.models.Post
import com.example.myapplication.MVVM.PostModule.data.repository.PostRepository
import kotlinx.coroutines.launch

class PostViewModel(private val postRepository : PostRepository) : ViewModel() {
     private val _listPost = MutableLiveData<List<Post>>()

     val listPost: LiveData<List<Post>> get() = _listPost

    fun getListPost()
    {
       viewModelScope.launch {
           _listPost.value = postRepository.getAllPost()
       }
    }

    fun insertPost(post: Post)
    {
        viewModelScope.launch {
            val result = postRepository.insertPost(post)
            if(result != -1L)
            {
                val updatedList = listPost.value?.toMutableList() ?: mutableListOf()
                updatedList.add(post)
                _listPost.value = updatedList
            }
        }
    }

    fun deletePost(post: Post)
    {
        viewModelScope.launch {
            val result = postRepository.deletePost(post)
            if(result > 0)
            {
                val updatedList = listPost.value?.toMutableList() ?: mutableListOf()
                updatedList.remove(post)
                _listPost.value = updatedList
            }
        }
    }

    fun updatePost(post: Post)
    {
       viewModelScope.launch {
           val result = postRepository.updatePost(post)
           if(result != -1)
           {
               val updatedList = listPost.value?.map {
                   if(it.id == post.id) post
                   else it
               } ?: listOf()
               _listPost.value = updatedList
           }
       }
    }

    fun changeLike(id: Int,value: Boolean)
    {
        viewModelScope.launch {
            val result = postRepository.updateIsLike(id, value)
            if(result != -1)
            {
                val updatedList = listPost.value?.map {
                    if(it.id == id) {
                        it.copy(isLike = value)
                    }else{
                        it
                    }
                } ?: listOf()

                _listPost.value = updatedList
            }
        }

    }
}