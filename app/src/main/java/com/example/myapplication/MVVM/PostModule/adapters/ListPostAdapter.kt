package com.example.myapplication.MVVM.PostModule.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MVVM.PostModule.data.models.Post
import com.example.myapplication.R
import com.example.myapplication.adapters.CustomRecyclerViewAdapter
import com.example.myapplication.api_operations.data.models.House

class ListPostAdapter(private var listData : List<Post>) :
    RecyclerView.Adapter<ListPostAdapter.ViewHolder>() {
    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val imageView : ImageView = itemView.findViewById(R.id.imagePathView)
        val postOwnerView : TextView = itemView.findViewById(R.id.postOwnerView)
        val favoriteStatusView : AppCompatImageButton = itemView.findViewById(R.id.favoriteStatusView)
        val createdPostTimeView : TextView = itemView.findViewById(R.id.createdPostTimeView)
        val likeCountView : TextView = itemView.findViewById(R.id.likeCountView)
        val postSubTitleView : TextView = itemView.findViewById(R.id.postSubTitleView)
        val postTitleView : TextView = itemView.findViewById(R.id.postTitleView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_post_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = listData[position]

        if(post.isLike)
        {
            val drawable = ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_favorite)
            holder.favoriteStatusView.setImageDrawable(drawable)
        }else{
            val drawable = ContextCompat.getDrawable(holder.itemView.context, R.drawable.ic_favorite_border)
            holder.favoriteStatusView.setImageDrawable(drawable)
        }

        holder.createdPostTimeView.text = post.createdTime
        holder.likeCountView.text = post.likeCount.toString()
        holder.postOwnerView.text = post.postOwner
        holder.postTitleView.text = post.title
        holder.postSubTitleView.text = post.subtitle

    }

    fun updateData(newList: List<Post>) {
        this.listData = newList
    }
}