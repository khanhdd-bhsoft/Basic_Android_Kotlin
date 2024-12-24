package com.example.myapplication.MVVM.PostModule.data.data_sources

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

abstract class MyRoomDB  : RoomDatabase() {

    abstract fun postDAO() : PostDAO
    companion object{
        @Volatile
        private var INSTANCE: MyRoomDB? = null
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context) : MyRoomDB
        {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDB::class.java,
                    "post_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}