package com.example.myapplication.api_operations.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.api_operations.data.models.House
import com.example.myapplication.api_operations.data.models.MarsPhoto

class ListHouseAdapter (private var listData : List<House>) :
    RecyclerView.Adapter<ListHouseAdapter.ViewHolder>() {

    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val houseNameView : TextView = itemView.findViewById(R.id.houseNameView)
        val houseElementView : TextView = itemView.findViewById(R.id.houseElementView)
        val houseAnimalsView : TextView = itemView.findViewById(R.id.houseAnimalsView)
        val houseColorsView : TextView = itemView.findViewById(R.id.houseColorsView)
        val houseCommonRoomsView : TextView = itemView.findViewById(R.id.commonRoomView)
        val houseFounderView : TextView = itemView.findViewById(R.id.houseFounderView)
        val houseHeadsView : ListView = itemView.findViewById(R.id.listHouseHeadRecyclerView)
        val houseTraitsView : ListView = itemView.findViewById(R.id.listHouseTraitView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_house_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val house: House = listData.get(position)

        holder.houseAnimalsView.text = house.animal
        holder.houseColorsView.text = house.hourColors
        holder.houseElementView.text = house.element
        holder.houseCommonRoomsView.text = house.commonRoom
        holder.houseNameView.text = house.name
        holder.houseFounderView.text = house.founder
//        holder.houseHeadsView.layoutManager = LinearLayoutManager(holder.itemView.context,LinearLayoutManager.HORIZONTAL,false)
        val houseHeadsView = ArrayAdapter(
            holder.itemView.context,
            android.R.layout.simple_list_item_1,
            house.heads.map { it.firstName + it.lastname }
        )
        holder.houseHeadsView.adapter = houseHeadsView


        val traitsAdapter = ArrayAdapter(
            holder.itemView.context,
            android.R.layout.simple_list_item_1,
            house.traits.map { it.name }
        )
        holder.houseTraitsView.adapter = traitsAdapter

        holder.itemView.setOnClickListener {
        }
    }

    fun updateData(newList: List<House>) {
        this.listData = newList
    }
}