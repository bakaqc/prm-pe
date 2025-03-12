package com.example.upskill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.upskill.screen.AppNavigation
import com.example.upskill.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val studentViewModel: StudentViewModel = viewModel()
            AppNavigation(navController = navController, viewModel = studentViewModel)
        }
    }
}
