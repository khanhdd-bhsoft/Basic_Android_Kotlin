package com.example.myapplication.MVVM.PostModule.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.MVVM.PostModule.adapters.ListPostAdapter
import com.example.myapplication.MVVM.PostModule.data.models.Post
import com.example.myapplication.MVVM.PostModule.data.repository.PostRepository
import com.example.myapplication.MVVM.PostModule.view_models.PostViewModel
import com.example.myapplication.R
import com.example.myapplication.api_operations.view_models.HouseViewModel
import com.example.myapplication.api_operations.view_models.MyViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PostHomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PostHomeFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private lateinit var recyclerView : RecyclerView
    private lateinit var listPostAdapter : ListPostAdapter
    private var listData : List<Post> = emptyList()
    private lateinit var postViewModel : PostViewModel
    private lateinit var postRepository: PostRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        initData()
        observeDataChange()
    }

    private fun initData(){
        postViewModel = ViewModelProvider(this,MyViewModelFactory(PostViewModel(postRepository)))[PostViewModel::class.java]
        postViewModel.getListPost()
    }

    private fun observeDataChange()
    {
        postViewModel.listPost.observe(this, Observer {
                listData= it
                listPostAdapter.notifyDataSetChanged()
            listPostAdapter.updateData(listData)
            }
        )
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_post_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.apply {
            val layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            listPostAdapter = ListPostAdapter(listData)
            adapter = listPostAdapter
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PostHomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}