package com.example.healthmonitorapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.healthmonitorapp.ui.theme.HealthMonitorAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthMonitorAppTheme {
                DashboardScreen(
                    healthEntries = HealthViewModel.healthEntries.collectAsState().value,
                    onAddEntryClick = {
                        startActivity(Intent(this, DataEntryActivity::class.java))
                    },
                    onBmiClick = {
                        startActivity(Intent(this, BMIActivity::class.java))
                    }
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DashboardScreen(
    healthEntries: List<String>,
    onAddEntryClick: () -> Unit,
    onBmiClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Health Dashboard") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary // Green theme
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Large Heart Icon
            Icon(
                imageVector = Icons.Default.Favorite, // Heart icon
                contentDescription = "Health Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(100.dp) // Large size
                    .padding(bottom = 16.dp)
            )

            // Title for Entries Section
            Text(
                text = "Welcome to Your Health Dashboard",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Divider(
                modifier = Modifier.padding(vertical = 8.dp),
                color = MaterialTheme.colorScheme.onBackground,
                thickness = 1.dp
            )

            // Entries List
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(healthEntries.size) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant // Soft green background for cards
                        ),
                        elevation = CardDefaults.cardElevation(8.dp),
                        shape = MaterialTheme.shapes.large
                    ) {
                        Text(
                            text = healthEntries[index],
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }

            // Add Entry Button
            Button(
                onClick = onAddEntryClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Entry",
                    modifier = Modifier.size(ButtonDefaults.IconSize + 8.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    "Add New Entry",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }

            // BMI Button
            Button(
                onClick = onBmiClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .height(64.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                shape = MaterialTheme.shapes.medium
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Calculate BMI",
                    modifier = Modifier.size(ButtonDefaults.IconSize + 8.dp),
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
                Text(
                    "Calculate BMI",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
