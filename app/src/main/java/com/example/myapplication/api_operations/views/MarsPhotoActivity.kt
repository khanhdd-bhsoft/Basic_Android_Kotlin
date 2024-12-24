package com.example.myapplication.api_operations.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api_operations.adapters.ListMarsPhotoAdapter
import com.example.myapplication.api_operations.adapters.ListQuoteAdapter
import com.example.myapplication.api_operations.data.models.MarsPhoto
import com.example.myapplication.api_operations.data.models.QuoteItem
import com.example.myapplication.api_operations.data.repository.MarsRepository
import com.example.myapplication.api_operations.data.repository.QuoteRepository
import com.example.myapplication.utils.ToolbarUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MarsPhotoActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var listQuoteView: RecyclerView
    private val repository = MarsRepository()
    private var listData = emptyList<MarsPhoto>()
    private lateinit var adapter: ListMarsPhotoAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mars_photo)
        initViews()
        fetchData()
    }

    private fun initViews()
    {
        toolbar = findViewById(R.id.marsPhotoToolbar)
        listQuoteView = findViewById(R.id.listMarsPhotoRecyclerView)
        setSupportActionBar(toolbar)
        ToolbarUtils().setupBasicToolbar(toolbar,{
            finish()
        },this)

        listQuoteView.layoutManager = GridLayoutManager(this@MarsPhotoActivity, 2)

        // data
        adapter = ListMarsPhotoAdapter(listData)
        listQuoteView.adapter = adapter
    }
    private fun fetchData()
    {
        /* approach 1
        var list: List<MarsPhoto> = emptyList()
        lifecycleScope.launch(Dispatchers.IO) {
            list = repository.getListPhoto()
            withContext(Dispatchers.Main) {
                listData = list
                adapter.updateData(list) // Phương thức updateData cần được thêm trong Adapter
                adapter.notifyDataSetChanged()
            }
        }
         */

        // approach 2
        lifecycleScope.launch {
            val list = repository.getPhotos()
            withContext(Dispatchers.Main) {
                listData = list
                adapter.updateData(list)
                adapter.notifyDataSetChanged()
            }
        }
    }
}