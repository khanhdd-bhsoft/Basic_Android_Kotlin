package com.example.myapplication.CustomServices

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.example.myapplication.R

class MyServices : Service() {

    private var mediaPlayer: MediaPlayer? = null
    private val binder = MusicBinder()



    override fun onCreate() {
        super.onCreate()
        Log.d("MusicService", "Service created")
    }
    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("MusicService", "Service started")
        val action = intent?.action
        when (action) {
            "PLAY" -> playMusic()
            "PAUSE" -> pauseMusic()
            "STOP" -> stopMusic()
        }
        return START_STICKY

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
        Log.d("MusicService", "Service destroyed")
    }



    // Service-bound methods
    fun playMusic() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.than_thoai) // Replace with your audio file
        }
        mediaPlayer?.apply {
            if (!isPlaying) {
                start()
                Log.d("MusicService", "Music playing")
            }
        }
    }

    fun pauseMusic() {
        mediaPlayer?.apply {
            if (isPlaying) {
                pause()
                Log.d("MusicService", "Music paused")
            }
        }
    }

    fun stopMusic() {
        mediaPlayer?.apply {
            stop()
            reset()
            Log.d("MusicService", "Music stopped")
        }
    }


    inner class MusicBinder : Binder() {
        fun getService(): MyServices = this@MyServices
    }

}