package com.example.upskill.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.upskill.model.Student
import com.example.upskill.viewmodel.StudentViewModel
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentListScreen(
    viewModel: StudentViewModel = viewModel(),
    onAddStudentClick: () -> Unit
) {
    val studentList by viewModel.students.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Student List - bakaqc",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddStudentClick,
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ) {
                Text("+", style = MaterialTheme.typography.titleLarge)
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Button(
                onClick = { viewModel.refreshStudents() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Refresh list of students", style = MaterialTheme.typography.bodyLarge)
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (studentList.isEmpty()) {
                Text(
                    text = "No student found!",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn {
                    items(studentList) { student ->
                        StudentItem(student = student, onDelete = { viewModel.deleteStudent(student) })
                    }
                }
            }
        }
    }
}

@Composable
fun StudentItem(student: Student, onDelete: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onDelete() },
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberImagePainter(
                    data = student.avatar,
                    builder = {
                        crossfade(true)
                        placeholder(android.R.drawable.ic_menu_report_image)
                    }
                ),
                contentDescription = "Avatar of ${student.first_name}",
                modifier = Modifier
                    .size(80.dp)
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(text = "${student.first_name} ${student.last_name}", style = MaterialTheme.typography.titleLarge)
                Text(text = student.email, style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
