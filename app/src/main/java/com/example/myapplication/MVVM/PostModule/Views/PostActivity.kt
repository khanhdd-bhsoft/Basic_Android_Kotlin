package com.example.myapplication.MVVM.PostModule.Views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.myapplication.Fragments.ArticleDetailFragment
import com.example.myapplication.Fragments.ItemFragment
import com.example.myapplication.Fragments.SettingsFragment
import com.example.myapplication.MVVM.PostModule.fragments.PostHomeFragment
import com.example.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class PostActivity : AppCompatActivity() {
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var newPostButton: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        initViews()
        setUpFragments()
        onListener()
    }

    private fun initViews(){
        newPostButton = findViewById(R.id.newPostButton)
        bottomNavigation = findViewById(R.id.post_navigation_bottom)
    }
    private fun setUpFragments()
    {
        replaceFragment(PostHomeFragment())
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment).commit()
    }

    private fun onListener()
    {
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_nav -> replaceFragment(PostHomeFragment())
                R.id.friend_nav -> replaceFragment(PostHomeFragment())
                R.id.exploration_nav -> replaceFragment(PostHomeFragment())
                R.id.profile_nav -> replaceFragment(PostHomeFragment())
            }
            true

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}