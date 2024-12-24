package com.example.myapplication.api_operations.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api_operations.adapters.ListHouseAdapter
import com.example.myapplication.api_operations.adapters.ListMarsPhotoAdapter
import com.example.myapplication.api_operations.data.models.House
import com.example.myapplication.api_operations.data.models.MarsPhoto
import com.example.myapplication.api_operations.data.repository.HouseRepository
import com.example.myapplication.api_operations.data.repository.MarsRepository
import com.example.myapplication.api_operations.view_models.HouseViewModel
import com.example.myapplication.api_operations.view_models.HouseViewModelFactory
import com.example.myapplication.api_operations.view_models.MyViewModelFactory
import com.example.myapplication.utils.ToolbarUtils

class HouseActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var listHouseRecyclerView: RecyclerView
    private lateinit var houseViewModel : HouseViewModel
    private var listData = emptyList<House>()
    private lateinit var adapter: ListHouseAdapter
    private lateinit var houseRepository: HouseRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house)
        initViews()
        fetchData()
    }
    private fun initViews()
    {
        toolbar = findViewById(R.id.houseToolbar)
        listHouseRecyclerView = findViewById(R.id.houseRecyclerView)
        setSupportActionBar(toolbar)
        ToolbarUtils().setupBasicToolbar(toolbar,{
            finish()
        },this)

        listHouseRecyclerView.layoutManager = LinearLayoutManager(this)

        // data
        houseRepository = HouseRepository()
        adapter = ListHouseAdapter(listData)
        listHouseRecyclerView.adapter = adapter
//        houseViewModel = ViewModelProvider(this,MyViewModelFactory(HouseViewModel(houseRepository)))[HouseViewModel::class.java]
        houseViewModel = ViewModelProvider(this,HouseViewModelFactory(houseRepository))[HouseViewModel::class.java]
    }
    private fun fetchData()
    {
        houseViewModel.getListHouse().observe(this, Observer {
            listData = it
            adapter.updateData(listData)
            adapter.notifyDataSetChanged()
        })
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }
}