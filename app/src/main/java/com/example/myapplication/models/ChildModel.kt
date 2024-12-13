package com.example.myapplication.models

data class ChildModel(override val id: Int, override val name: String,override val birthday: String, val studentId: String, val className: String) : BaseModel(id, name, birthday)

