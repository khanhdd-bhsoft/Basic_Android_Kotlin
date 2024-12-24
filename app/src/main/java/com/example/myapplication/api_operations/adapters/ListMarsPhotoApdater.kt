package com.example.myapplication.api_operations.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.api_operations.data.models.MarsPhoto

class ListMarsPhotoAdapter(private var listData: List<MarsPhoto>) : RecyclerView.Adapter<ListMarsPhotoAdapter.ViewHolder>() {

    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val image : ImageView = itemView.findViewById(R.id.marsPhotoView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_mars_photo_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo: MarsPhoto = listData[position]
        Glide.with(holder.itemView.context).load(photo.imageSrc).placeholder(R.drawable.admin).into(holder.image)
        holder.itemView.setOnClickListener {
        }
    }

    fun updateData(newList: List<MarsPhoto>) {
        this.listData = newList
    }
}