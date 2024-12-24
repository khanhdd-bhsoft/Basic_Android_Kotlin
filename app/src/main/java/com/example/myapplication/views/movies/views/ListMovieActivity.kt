package com.example.myapplication.views.movies.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Repositories.StudentRepositories
import com.example.myapplication.models.Student
import com.example.myapplication.utils.ToolbarUtils
import com.example.myapplication.views.movies.adapters.ListMovieAdapter
import com.example.myapplication.views.movies.data.Movie
import com.example.myapplication.views.movies.data.MovieRepository
import com.example.myapplication.views.movies.listeners.MovieActionListener
import com.example.myapplication.views.student_management.adapter.StudentListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListMovieActivity : AppCompatActivity(), MovieActionListener {

    lateinit var listMovieRecyclerView: RecyclerView
    lateinit var searchView: SearchView
    lateinit var toolbar: Toolbar
    lateinit var floatingButton: FloatingActionButton
    //
    lateinit var adapter: ListMovieAdapter
    lateinit var movieRepo: MovieRepository
      var listMovie: List<Movie> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_movie)
        initViews()
        getData()
        onCreateNewMovieClick()
    }
    private fun initViews()
    {
        floatingButton = findViewById(R.id.newMovieButton)
        toolbar = findViewById(R.id.toolbarListMovie)
        searchView = findViewById(R.id.searchMovieView)
        listMovieRecyclerView = findViewById(R.id.listMovieRecyclerView)
        movieRepo = MovieRepository(this)

        setSupportActionBar(toolbar)
        ToolbarUtils().setupBasicToolbar(toolbar,{
            finish()
        },this)
    }
    private fun getData() {
        CoroutineScope(Dispatchers.IO).launch {
            val movies = movieRepo.getAllMovie() // Truy vấn trên background thread
            withContext(Dispatchers.Main) {
                // Cập nhật adapter và RecyclerView trên main thread
                listMovie = movies
                adapter = ListMovieAdapter(listMovie, this@ListMovieActivity)
                listMovieRecyclerView.layoutManager = GridLayoutManager(this@ListMovieActivity, 2)
                listMovieRecyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }
    }
    private fun onCreateNewMovieClick()
    {
        floatingButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("New student")
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.alert_layout_movie, null)
            val movieRatingET  = dialogLayout.findViewById<EditText>(R.id.movieRatingET)
            val movieSubtitleET  = dialogLayout.findViewById<EditText>(R.id.movieSubtitleET)
            val movieTitleET  = dialogLayout.findViewById<EditText>(R.id.movieTitleET)
            builder.setView(dialogLayout)
            builder.setPositiveButton("Save") {
                    dialogInterface, i ->
                val rating = movieRatingET.text.toString().toDouble()
                var subtitle = movieSubtitleET.text.toString()
                var title = movieTitleET.text.toString()
                val movie = Movie(title = title,subtitle = subtitle,rating = rating)

                lifecycleScope.launch (Dispatchers.IO){
                    movieRepo.insertMovie(movie)
                    withContext(Dispatchers.Main) {
                        fetchData()  // Fetch and update UI on main thread
                    }
                }

                dialogInterface.dismiss()
            }
            builder.setNegativeButton("Cacel") {
                    dialogInterface, i ->
                dialogInterface.cancel()
            }
            builder.show()
        }
    }
    private fun fetchData()
    {
        CoroutineScope(Dispatchers.IO).launch {
            val updatedList = movieRepo.getAllMovie()
            withContext(Dispatchers.Main) {
                adapter.updateData(updatedList)
            }
        }
    }

    override fun editMovie(movie: Movie): Int {
        var result: Int = 0
        lifecycleScope.launch(Dispatchers.IO) {
            result = movieRepo.updateMovie(movie)
            if(result > 0)
            {
                withContext(Dispatchers.Main) {
                    fetchData()  // Fetch and update UI on main thread
                }
            }
        }
        return  result
    }

    override fun deleteMovie(movie: Movie) {
        lifecycleScope.launch(Dispatchers.IO) {
            movieRepo.deleteMovie(movie)
            withContext(Dispatchers.Main) {
                fetchData()  // Fetch and update UI on main thread
            }
        }

    }
}