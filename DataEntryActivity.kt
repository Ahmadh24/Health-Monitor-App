package com.example.healthmonitorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthmonitorapp.ui.theme.HealthMonitorAppTheme


class DataEntryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthMonitorAppTheme {
                DataEntryScreen(
                    onSubmitClick = { heartRate, bloodSugar, date ->
                        val entry = "Heart Rate: $heartRate bpm, Blood Sugar: $bloodSugar mg/dL Date: $date"
                        HealthViewModel.addHealthEntry(entry) // Update ViewModel
                        finish() // Close activity and go back to Dashboard
                    }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DataEntryScreen(onSubmitClick: (String, String, String) -> Unit) {
    val heartRate = remember { mutableStateOf("") }
    val bloodSugar = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Health Entry") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = heartRate.value,
                onValueChange = { heartRate.value = it },
                label = { Text("Heart Rate (bpm)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = bloodSugar.value,
                onValueChange = { bloodSugar.value = it },
                label = { Text("Blood Sugar (mg/dL)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = date.value,
                onValueChange = { date.value = it },
                label = { Text("Date") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { onSubmitClick(heartRate.value, bloodSugar.value, date.value) },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("Submit")
            }
        }
    }
}

