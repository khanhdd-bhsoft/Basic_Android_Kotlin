package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

class MainActivity2 : AppCompatActivity() {
    val listValue: ArrayList<String> = arrayListOf<String>("ABC","ffsad","AAA","BUG","AQWE","AAAA","DDDD","ABC","ffsad","AAA","BUG","AQWE","AAAA","DDDD","ABC","ffsad","AAA","BUG","AQWE","AAAA","DDDD");
    lateinit var listView: ListView
    lateinit var appBar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initializeViews()
        onHandleClick()
    }
    private fun initializeViews(){
        listView = findViewById(R.id.customListView)
        appBar = findViewById(R.id.actionBar)
        setSupportActionBar(appBar)
        appBar.title = "My App Title"
        appBar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.back_icon)
        appBar.setNavigationOnClickListener {
            finish()
        }
    }
    private fun onHandleClick(){
        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, _, position, _ ->
                val item = parent.getItemAtPosition(position)
                Toast.makeText(this, "Item clicked: $item", Toast.LENGTH_SHORT).show()
            }

        listView.setOnItemLongClickListener { parent, view, position, id ->
            val item = parent.getItemAtPosition(position)
//            Toast.makeText(this, "Item long clicked: $item", Toast.LENGTH_SHORT).show()

            intent= Intent(Intent.ACTION_VIEW, Uri.parse("https://www.javatpoint.com/"))
            startActivity(intent)
            true
        }
    }



}