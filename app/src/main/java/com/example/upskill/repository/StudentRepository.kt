package com.example.upskill.repository

import androidx.lifecycle.LiveData
import com.example.upskill.data.StudentDao
import com.example.upskill.model.Student
import com.example.upskill.network.RetrofitInstance

class StudentRepository(private val studentDao: StudentDao) {

    val allStudents: LiveData<List<Student>> = studentDao.getAllStudents()

    suspend fun refreshStudents() {
        val response = RetrofitInstance.api.getStudents().data
        response.forEach { studentDao.insertStudent(it) }
    }

    suspend fun insertStudent(student: Student) {
        studentDao.insertStudent(student)
    }

    suspend fun deleteStudent(student: Student) {
        studentDao.deleteStudent(student)
    }
}
