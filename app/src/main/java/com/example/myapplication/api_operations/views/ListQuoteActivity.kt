package com.example.myapplication.api_operations.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api_operations.adapters.ListQuoteAdapter
import com.example.myapplication.api_operations.data.models.QuoteItem
import com.example.myapplication.api_operations.data.models.QuoteList
import com.example.myapplication.api_operations.data.repository.QuoteRepository
import com.example.myapplication.api_operations.listeners.OnQuoteItemClickListener
import com.example.myapplication.utils.ToolbarUtils

class ListQuoteActivity : AppCompatActivity(),OnQuoteItemClickListener {
    private lateinit var toolbar: Toolbar
    private lateinit var listQuoteView: RecyclerView
    private val repository = QuoteRepository()
    private var listQuoteData = emptyList<QuoteItem>()
    private lateinit var adapter: ListQuoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_quote)
        initViews()
        fetchData()
    }
    private fun initViews()
    {
        toolbar = findViewById(R.id.listQuoteToolbar)
        listQuoteView = findViewById(R.id.listQuoteRecyclerView)
        setSupportActionBar(toolbar)
        ToolbarUtils().setupBasicToolbar(toolbar,{
            finish()
        },this)

        // data
        adapter = ListQuoteAdapter(listQuoteData,this)
    }
    private fun fetchData()
    {
        repository.getQuotesWithoutReturnValue(onSuccess = {
            it ->
            runOnUiThread {
                listQuoteData = it?.results ?: emptyList()
                adapter.notifyDataSetChanged()
            }
        },
        onFailure = {
            it ->
                Toast.makeText(this,it,Toast.LENGTH_LONG)
        })

    }

    override fun onItemClick(quoteItem: QuoteItem) {

    }
}