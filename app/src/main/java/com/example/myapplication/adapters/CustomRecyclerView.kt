package com.example.myapplication.adapters

import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.models.ItemsViewModel



class CustomRecyclerViewAdapter(private var listData: List<ItemsViewModel>) : RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val titleView: TextView = itemView.findViewById(R.id.titleOfItemView)
        val subtitleView: TextView = itemView.findViewById(R.id.subTitleOfItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData = listData[position]

        holder.imageView.setImageResource(itemData.image)
        holder.titleView.text = itemData.title
        holder.subtitleView.text = itemData.subtitle
    }


}