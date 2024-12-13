package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.myapplication.Fragments.SettingsFragment

class SettingsActivity : AppCompatActivity() {
    private lateinit var frameSettingLayout: FrameLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        init()
        setUpView()
    }
    private fun init()
    {
        frameSettingLayout = findViewById(R.id.frameSettingLayout)
    }
    private fun setUpView()
    {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val settingFragment = SettingsFragment()
        fragmentTransaction.replace(R.id.frameSettingLayout, settingFragment).commit()
    }

    override fun onBackPressed() {
        finish()
    }
}