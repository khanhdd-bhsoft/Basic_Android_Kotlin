package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapters.CustomRecyclerViewAdapter
import com.example.myapplication.models.ItemsViewModel

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var listView: RecyclerView
    private lateinit var listData: ArrayList<ItemsViewModel>
    private lateinit var customAdapter: CustomRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        initializeViews()
        generateData()
        setUpRecyclerView()
    }

    private fun initializeViews(){
        listView = findViewById(R.id.recyclerView)

    }
    private fun generateData(){
        listData = ArrayList<ItemsViewModel>()
        for(i in 1..20){
            listData.add(ItemsViewModel(R.drawable.admin,"Title of $i","subtitle of $i"))
        }

    }
    private fun setUpRecyclerView(){
        listView.layoutManager = LinearLayoutManager(this)
        customAdapter = CustomRecyclerViewAdapter(listData)
        listView.adapter = customAdapter
    }

    override fun onBackPressed() {
        finish()
    }

}