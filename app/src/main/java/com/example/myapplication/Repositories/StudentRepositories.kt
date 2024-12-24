package com.example.myapplication.Repositories

import android.content.ContentValues
import android.content.Context
import com.example.myapplication.data.local_db.StudentDbHelper
import com.example.myapplication.data.local_db.StudentTableHelper
import com.example.myapplication.models.Student

class StudentRepositories(context: Context) {
    private val databaseHelper: StudentDbHelper = StudentDbHelper(context)



    fun insertData(student: Student)
    {
        val db = databaseHelper.writableDatabase
      val values =  ContentValues().apply {
            put(StudentTableHelper.StudentEntity.COLUMN_NAME,student.name)
            put(StudentTableHelper.StudentEntity.COLUMN_CLASS_NAME,student.className)
            put(StudentTableHelper.StudentEntity.COLUMN_STUDENT_CODE,student.studentCode)
            put(StudentTableHelper.StudentEntity.COLUMN_AVERAGE_SCORE,student.averageScore)
        }
        db.insert(StudentTableHelper.StudentEntity.TABLE_NAME,null,values)
        db.close()
    }

    fun getStudentList(): List<Student>
    {
        val db = databaseHelper.readableDatabase
        val list = mutableListOf<Student>()
        val cursor = db.rawQuery("SELECT * FROM ${StudentTableHelper.StudentEntity.TABLE_NAME}",null)
        while (cursor.moveToNext())
        {
            val nameColumnIndex = cursor.getColumnIndex(StudentTableHelper.StudentEntity.COLUMN_NAME)
            val classNameColumnIndex = cursor.getColumnIndex(StudentTableHelper.StudentEntity.COLUMN_CLASS_NAME)
            val codeColumnIndex = cursor.getColumnIndex(StudentTableHelper.StudentEntity.COLUMN_NAME)
            val scoreColumnIndex = cursor.getColumnIndex(StudentTableHelper.StudentEntity.COLUMN_CLASS_NAME)
            if(nameColumnIndex!=-1 && classNameColumnIndex!= -1 && codeColumnIndex!=-1 && scoreColumnIndex!=-1  )
            {
                val name = cursor.getString(nameColumnIndex)
                val code = cursor.getString(codeColumnIndex)
                val className = cursor.getString(classNameColumnIndex)
                val score = cursor.getDouble(scoreColumnIndex)
                list.add(Student(name,className, code, score))
            }
        }
        cursor.close()
        db.close()
        return list
    }



}