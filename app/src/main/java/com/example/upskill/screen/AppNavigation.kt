package com.example.upskill.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.upskill.viewmodel.StudentViewModel

sealed class Screen(val route: String) {
    object StudentList : Screen("student_list")
    object AddStudent : Screen("add_student")
}

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController(), viewModel: StudentViewModel) {
    NavHost(navController = navController, startDestination = Screen.StudentList.route) {
        composable(Screen.StudentList.route) {
            StudentListScreen(
                viewModel = viewModel,
                onAddStudentClick = { navController.navigate(Screen.AddStudent.route) }
            )
        }
        composable(Screen.AddStudent.route) {
            AddStudentScreen(viewModel = viewModel, onStudentAdded = {
                navController.popBackStack()
            })
        }
    }
}

