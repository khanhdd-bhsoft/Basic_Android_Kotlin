package com.example.myapplication.data.local_db

import android.provider.BaseColumns

object StudentTableHelper {
    object StudentEntity : BaseColumns {
        const val TABLE_NAME = "students"
        const val COLUMN_NAME = "name"
        const val COLUMN_STUDENT_CODE = "student_code"
        const val COLUMN_CLASS_NAME = "class_name"
        const val COLUMN_AVERAGE_SCORE = "average_score"
    }

    val SQL_CREATE_TABLE = "CREATE TABLE ${StudentEntity.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
            "${StudentEntity.COLUMN_NAME} TEXT, " +
            "${StudentEntity.COLUMN_STUDENT_CODE} TEXT, " +
            "${StudentEntity.COLUMN_CLASS_NAME} TEXT, " +
            "${StudentEntity.COLUMN_AVERAGE_SCORE} REAL )"

    val SQL_DROP_TABLE = "DROP TABLE IF EXIST ${StudentEntity.TABLE_NAME}"
}