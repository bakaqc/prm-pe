package com.example.upskill

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.example.upskill.model.Student
import com.example.upskill.viewmodel.StudentViewModel

class MainActivity : ComponentActivity() {
    private val studentViewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StudentApp(studentViewModel)
        }
    }
}

@Composable
fun StudentApp(studentViewModel: StudentViewModel) {
    val students by studentViewModel.allStudents.observeAsState(emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(40.dp)) {
        Button(
            onClick = { studentViewModel.refreshStudents() },
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp)
        ) {
            Text("Tải danh sách sinh viên")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(students) { student ->
                StudentItem(student)
            }
        }
    }
}

@Composable
fun StudentItem(student: Student) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberImagePainter(student.avatar),
                contentDescription = "Student Avatar",
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(text = "${student.first_name} ${student.last_name}", style = MaterialTheme.typography.titleMedium)
                Text(text = student.email, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
