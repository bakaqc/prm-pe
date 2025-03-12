package com.example.upskill.data

import androidx.room.*
import com.example.upskill.model.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {

    @Query("SELECT * FROM students")
    fun getAllStudents(): Flow<List<Student>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student: Student)

    @Delete
    suspend fun deleteStudent(student: Student)

    @Query("SELECT * FROM students WHERE id = :studentId")
    fun getStudentById(studentId: Int): Flow<Student?>
}
