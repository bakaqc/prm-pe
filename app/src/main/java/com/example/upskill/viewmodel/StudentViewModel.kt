package com.example.upskill.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.upskill.data.StudentDatabase
import com.example.upskill.model.Student
import com.example.upskill.repository.StudentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StudentRepository

    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students

    init {
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)

        viewModelScope.launch {
            repository.allStudents.collectLatest { studentList ->
                _students.value = studentList
            }
        }
    }

    fun refreshStudents() = viewModelScope.launch {
        repository.refreshStudents()
    }

    fun addStudent(student: Student) = viewModelScope.launch {
        repository.insertStudent(student)
    }

    fun deleteStudent(student: Student) = viewModelScope.launch {
        repository.deleteStudent(student)
    }

    fun getStudentById(studentId: Int, onResult: (Student?) -> Unit) {
        viewModelScope.launch {
            val student = repository.getStudentById(studentId)
            onResult(student)
        }
    }
}

