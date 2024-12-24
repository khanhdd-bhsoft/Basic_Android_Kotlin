package com.example.myapplication.views.student_management.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Repositories.StudentRepositories
import com.example.myapplication.data.local_db.StudentDbHelper
import com.example.myapplication.models.Student
import com.example.myapplication.utils.ToolbarUtils
import com.example.myapplication.views.student_management.adapter.StudentListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListStudentActivity : AppCompatActivity() {
    lateinit var listStudentRecyclerView: RecyclerView
    lateinit var searchView: SearchView
    lateinit var toolbar: Toolbar
    lateinit var floatingButton: FloatingActionButton
    //
    lateinit var adapter: StudentListAdapter
    lateinit var studentRepo: StudentRepositories
    var listStudent: List<Student> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_student)
        initView()
        getData()
        onClickHandle()
    }
    private fun initView()
    {
        toolbar = findViewById(R.id.toolBarStudentList)
        listStudentRecyclerView = findViewById(R.id.listStudentRecyclerView)
        searchView = findViewById(R.id.searchStudentView)
        floatingButton = findViewById(R.id.insertStudentButton)
        studentRepo = StudentRepositories(this)
        searchView.queryHint = "Search for students"

        setSupportActionBar(toolbar)

        setSupportActionBar(toolbar)
        ToolbarUtils().setupBasicToolbar(toolbar,{
            finish()
        },this)

    }
    private fun getData()
    {
        listStudent = studentRepo.getStudentList()
        adapter = StudentListAdapter(listStudent)
        listStudentRecyclerView.adapter = adapter
        listStudentRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.notifyDataSetChanged()
    }
    private fun onClickHandle()
    {
        floatingButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("New student")
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.alert_dialog_with_edittext, null)
            val studentNameET  = dialogLayout.findViewById<EditText>(R.id.studentNameET)
            val studentCodeET  = dialogLayout.findViewById<EditText>(R.id.studentCodeET)
            val studentClassNameET  = dialogLayout.findViewById<EditText>(R.id.studentClassNameET)
            val studentScoreET  = dialogLayout.findViewById<EditText>(R.id.studentScoreET)
            builder.setView(dialogLayout)
            builder.setPositiveButton("Save") {
                    dialogInterface, i ->
                val name = studentNameET.text.toString()
                val score: Double =  studentScoreET.text.toString().toDouble()
                var className = studentClassNameET.text.toString()
                var code = studentCodeET.text.toString()
                val student = Student(name,className,code,score)
                studentRepo.insertData(student)
                dialogInterface.dismiss()
            }
            builder.setNegativeButton("Cacel") {
                    dialogInterface, i ->
                dialogInterface.cancel()
            }
            builder.show()
        }
    }
}