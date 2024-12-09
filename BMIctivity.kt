package com.example.healthmonitorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthmonitorapp.ui.theme.HealthMonitorAppTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

class BMIActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthMonitorAppTheme {
                BMICalculatorScreen(onBackPress = { finish() }) // Call `finish()` to go back
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BMICalculatorScreen(onBackPress: () -> Unit) {
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmiResult by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("BMI Calculator") },
                navigationIcon = {
                    IconButton(onClick = onBackPress) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Input for Weight in Pounds
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                label = { Text("Weight (lbs)") },
                modifier = Modifier.fillMaxWidth()
            )

            // Input for Height in Inches
            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                label = { Text("Height (inches)") },
                modifier = Modifier.fillMaxWidth()
            )

            // Calculate Button
            Button(
                onClick = {
                    val weightValue = weight.toDoubleOrNull()
                    val heightValue = height.toDoubleOrNull()

                    if (weightValue != null && heightValue != null && heightValue > 0) {
                        val bmi = (weightValue / (heightValue * heightValue)) * 703
                        bmiResult = String.format("Your BMI: %.2f", bmi)
                    } else {
                        bmiResult = "Please enter valid weight and height."
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Calculate BMI")
            }

            // Display BMI Result
            if (bmiResult.isNotEmpty()) {
                Text(
                    text = bmiResult,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BMIPreview() {
    HealthMonitorAppTheme {
        BMICalculatorScreen(onBackPress = {})
    }
}
