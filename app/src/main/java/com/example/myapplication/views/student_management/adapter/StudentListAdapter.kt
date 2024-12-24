package com.example.myapplication.views.student_management.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.CustomRecyclerViewAdapter
import com.example.myapplication.models.Student

class StudentListAdapter(private var listData: List<Student>) : RecyclerView.Adapter<StudentListAdapter.ViewHolder>() {
    class ViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val nameView: TextView = itemView.findViewById(R.id.studentName)
        val classNameView: TextView = itemView.findViewById(R.id.studentClassName)
        val averageScoreView: TextView = itemView.findViewById(R.id.studentAverageScore)
        val codeView: TextView = itemView.findViewById(R.id.studentCode)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.student_single_view,parent,false)
        return StudentListAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return  listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var student = listData.get(position)

        holder.averageScoreView.text =  "${student.averageScore}"
        holder.classNameView.text = student.className
        holder.codeView.text = student.studentCode
        holder.nameView.text = student.name
    }
}