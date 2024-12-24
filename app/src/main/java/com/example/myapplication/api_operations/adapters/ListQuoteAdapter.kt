package com.example.myapplication.api_operations.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.CustomRecyclerViewAdapter
import com.example.myapplication.api_operations.data.models.QuoteItem
import com.example.myapplication.api_operations.listeners.OnQuoteItemClickListener

class ListQuoteAdapter(private var listData: List<QuoteItem>,private val itemClickListener: OnQuoteItemClickListener) : RecyclerView.Adapter<ListQuoteAdapter.ViewHolder>() {
    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val contentQuoteView : TextView = itemView.findViewById(R.id.quoteContentView)
        val authorQuoteView : TextView = itemView.findViewById(R.id.quoteAuthorView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_quotes_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quoteItem: QuoteItem = listData.get(position)

        holder.authorQuoteView.text = quoteItem.author
        holder.contentQuoteView.text = quoteItem.content

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(quoteItem)
        }
    }
}