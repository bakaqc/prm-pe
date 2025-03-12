package com.example.upskill.network

import com.example.upskill.model.Student
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface StudentApiService {
    @GET("users?page=1")
    suspend fun getStudents(): StudentResponse
}

data class StudentResponse(val data: List<Student>)

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://reqres.in/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: StudentApiService by lazy {
        retrofit.create(StudentApiService::class.java)
    }
}
