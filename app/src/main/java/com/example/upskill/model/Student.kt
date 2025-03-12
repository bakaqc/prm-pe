package com.example.upskill.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
) {
    constructor(email: String, first_name: String, last_name: String, avatar: String)
            : this(0, email, first_name, last_name, avatar)
}
