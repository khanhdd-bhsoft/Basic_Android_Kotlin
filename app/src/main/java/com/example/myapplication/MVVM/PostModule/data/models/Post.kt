package com.example.myapplication.MVVM.PostModule.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "post_table")
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "subtitle") val subtitle: String,
    @ColumnInfo(name = "likeCount") val likeCount: Int,
    @ColumnInfo(name = "isLike") val isLike: Boolean,
    @ColumnInfo(name = "created_time") val createdTime: String,
    @ColumnInfo(name = "post_owner") val postOwner : String,
    @ColumnInfo(name = "image_path") val imagePath : String,
)
