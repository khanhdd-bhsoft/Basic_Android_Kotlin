package com.example.myapplication.CustomServices

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.utils.ToolbarUtils

class MusicPlayerActivity : AppCompatActivity() {
    private lateinit var songNameView: TextView
    private lateinit var imageView: ImageView
    private lateinit var seekBar: AppCompatSeekBar
    private lateinit var previousSongButton: ImageButton
    private lateinit var nextSongButton: ImageButton
    private lateinit var pauseOrPlaySongButton: ImageButton
    private lateinit var toolbar: Toolbar

    private var musicService: MyServices? = null
    private var isBound = false

    private var isPlaying = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as MyServices.MusicBinder
            musicService = binder.getService()
            isBound = true
            Log.d("MusicPlayerActivity", "Service connected")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBound = false
            musicService = null
            Log.d("MusicPlayerActivity", "Service disconnected")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music_player)
        initViews()
        val intent = Intent(this, MyServices::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        onHandleClick()
    }
    private fun initViews()
    {
        songNameView = findViewById(R.id.songNameView)
        imageView = findViewById(R.id.songImageView)
        seekBar = findViewById(R.id.processSeekbar)
        previousSongButton = findViewById(R.id.previousSongButton)
        nextSongButton = findViewById(R.id.nextSongButton)
        pauseOrPlaySongButton = findViewById(R.id.pauseOrPlayButton)
        toolbar = findViewById(R.id.musicPlayerToolbar)
        setSupportActionBar(toolbar)
        ToolbarUtils().setupBasicToolbar(toolbar, {
            finish()
        },this)
    }
    private fun onHandleClick()
    {
        pauseOrPlaySongButton.setOnClickListener {
            if(isPlaying)
            {
                pauseOrPlaySongButton.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.play_circle))
                musicService?.pauseMusic()
            }else{
                pauseOrPlaySongButton.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.pause_circle))
                musicService?.playMusic()
            }
            isPlaying = !isPlaying
        }
    }
}