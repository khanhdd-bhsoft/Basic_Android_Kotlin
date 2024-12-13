package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.myapplication.Fragments.ArticleDetailFragment
import com.example.myapplication.Fragments.ItemFragment
import com.example.myapplication.Fragments.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MuitlFragmentActivity : AppCompatActivity() {

//    private lateinit var navController: NavController
    private lateinit var bottomNavigation: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_muitl_fragment)
        init()
        setUpFragments()
        onListener()
    }

    private fun init()
    {
//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//        navController = navHostFragment.navController
//
//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
//        NavigationUI.setupWithNavController(bottomNavigationView, navController)
        bottomNavigation = findViewById(R.id.bottom_nav_view)
    }
    private fun setUpFragments()
    {
        replaceFragment(SettingsFragment())
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
                R.id.navigation_home -> replaceFragment(ItemFragment())
                R.id.navigation_explore -> replaceFragment(ArticleDetailFragment())
                R.id.navigation_settings -> replaceFragment(SettingsFragment())
            }
             true

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}