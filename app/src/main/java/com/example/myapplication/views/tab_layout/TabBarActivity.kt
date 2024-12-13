package com.example.myapplication.views.tab_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabBarActivity : AppCompatActivity() {

    private lateinit var tabToolbar: Toolbar
    private lateinit var tabLayoutView: TabLayout
    private lateinit var viewPager: ViewPager2
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_bar)
        initView()
        setupViewPager()
        TabLayoutMediator(tabLayoutView, viewPager) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> {
                    tab.text = "Friends"
                    tab.icon= ContextCompat.getDrawable(this, R.drawable.friend)
                }
                1 -> {
                    tab.text = "Friends request"
                    tab.icon= ContextCompat.getDrawable(this, R.drawable.friend_request)
                }

            }
        }.attach()
    }
    private fun initView()
    {
        tabToolbar = findViewById(R.id.tab_toolbar)
        tabLayoutView = findViewById(R.id.tabLayoutView)
        viewPager = findViewById(R.id.tab_viewpager)
        setSupportActionBar(tabToolbar)
    }
    private fun setupViewPager(){
        var adapter: MyFragmentStateAdapter = MyFragmentStateAdapter(this)
        viewPager.adapter = adapter

    }

}