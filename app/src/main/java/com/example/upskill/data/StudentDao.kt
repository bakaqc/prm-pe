package com.example.upskill.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.upskill.model.Student

@Dao
interface StudentDao {
    @Query("SELECT * FROM students ORDER BY first_name, last_name")
    fun getAllStudents(): LiveData<List<Student>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("DELETE FROM students")
    suspend fun clearAll()
}
