package com.example.upskill.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.upskill.data.StudentDatabase
import com.example.upskill.model.Student
import com.example.upskill.repository.StudentRepository
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: StudentRepository
    val allStudents: LiveData<List<Student>>

    init {
        val studentDao = StudentDatabase.getDatabase(application).studentDao()
        repository = StudentRepository(studentDao)
        allStudents = repository.allStudents
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
}
