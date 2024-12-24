package com.example.myapplication.views.movies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.CustomRecyclerViewAdapter
import com.example.myapplication.views.movies.data.Movie
import com.example.myapplication.views.movies.listeners.MovieActionListener

class ListMovieAdapter(private var listData: List<Movie>,  private val movieActionListener: MovieActionListener) :
    RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {
    class  ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        val deleteIconButton: ImageButton = itemView.findViewById(R.id.deleteMovieIcon)
        val editMovieIcon: ImageButton = itemView.findViewById(R.id.editMovieIcon)
        val movieTitle: TextView = itemView.findViewById(R.id.movieTitleTextView)
        val movieSubtitle: TextView = itemView.findViewById(R.id.movieSubtitleTextView)
        val movieRating: TextView = itemView.findViewById(R.id.movieRatingTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.single_movie_view,parent,false)
        return ListMovieAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie: Movie = listData.get(position)
        holder.movieRating.text = movie.rating.toString()
        holder.movieSubtitle.text = movie.subtitle
        holder.movieTitle.text = movie.title
        holder.editMovieIcon.setOnClickListener {
            movieActionListener.editMovie(movie)
        }
        holder.deleteIconButton.setOnClickListener {
            movieActionListener.deleteMovie(movie)
        }
    }
    fun updateData(newData: List<Movie>) {
        listData = newData
        notifyDataSetChanged()
    }
}