package com.example.myapplication.api_operations.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api_operations.adapters.ListQuoteAdapter
import com.example.myapplication.api_operations.adapters.ListTicketAdapter
import com.example.myapplication.api_operations.data.models.QuoteItem
import com.example.myapplication.api_operations.data.models.Ticket
import com.example.myapplication.api_operations.data.repository.QuoteRepository
import com.example.myapplication.api_operations.data.repository.TicketRepository
import com.example.myapplication.utils.ToolbarUtils
import kotlinx.coroutines.launch

class TicketActivity : AppCompatActivity() {
    private lateinit var toolbar: Toolbar
    private lateinit var listTicketRecyclerView: RecyclerView
    private val repository = TicketRepository()
    private var listTicketData = emptyList<Ticket>()
    private lateinit var adapter: ListTicketAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        initViews()
        fetchData()
    }

    private fun initViews()
    {
        toolbar = findViewById(R.id.listTicketToolbar)
        listTicketRecyclerView = findViewById(R.id.listTicketRecyclerView)
        setSupportActionBar(toolbar)
        ToolbarUtils().setupBasicToolbar(toolbar,{
            finish()
        },this)

        // data
        adapter = ListTicketAdapter(listTicketData)
        listTicketRecyclerView.adapter = adapter
    }
    private fun fetchData()
    {
        lifecycleScope.launch {
            repository.getTickets(onSuccess = {
                    it ->
                runOnUiThread {
                    listTicketData = it
                    adapter.notifyDataSetChanged()
                }
            },
                onFailure = {
                        it ->
                })
        }

    }
}