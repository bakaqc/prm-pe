package com.example.upskill.repository

import com.example.upskill.data.StudentDao
import com.example.upskill.model.Student
import com.example.upskill.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class StudentRepository(private val studentDao: StudentDao) {

    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()

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

    suspend fun getStudentById(studentId: Int): Student? {
        return studentDao.getStudentById(studentId).first()
    }
}
