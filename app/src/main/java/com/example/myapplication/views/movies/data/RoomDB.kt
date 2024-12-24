@file:OptIn(InternalCoroutinesApi::class)

package com.example.myapplication.views.movies.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = arrayOf(Movie::class), version = 1)
abstract class RoomDB : RoomDatabase() {
    abstract fun movieDao() : MovieDAO
    companion object{
        @Volatile
        private var INSTANCE: RoomDB? = null
        fun getDatabase(context: Context) : RoomDB
        {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "movie_database"
                ).build()
                INSTANCE =  instance
                instance
            }
        }
    }
}