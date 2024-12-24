package com.example.myapplication.api_operations.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.api_operations.data.models.QuoteItem
import com.example.myapplication.api_operations.data.models.Ticket
import com.example.myapplication.api_operations.listeners.OnQuoteItemClickListener

class ListTicketAdapter(private var listData: List<Ticket>) : RecyclerView.Adapter<ListTicketAdapter.ViewHolder>()  {


    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val ticketNameView : TextView = itemView.findViewById(R.id.ticketNameView)
        val ticketRankView: TextView = itemView.findViewById(R.id.ticketRankView)
        val ticketSymbolView: TextView = itemView.findViewById(R.id.ticketSymbolView)
        val priceUsdView: TextView = itemView.findViewById(R.id.priceUsdView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_ticket_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ticket: Ticket = listData.get(position)

        holder.ticketNameView.text = ticket.name
        holder.priceUsdView.text = ticket.priceUsd
        holder.ticketRankView.text = ticket.rank.toString()
        holder.ticketSymbolView.text = ticket.symbol

        holder.itemView.setOnClickListener {
        }
    }
}