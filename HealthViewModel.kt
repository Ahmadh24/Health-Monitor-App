package com.example.healthmonitorapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object HealthViewModel : ViewModel() {
    private val _healthEntries = MutableStateFlow<List<String>>(emptyList())
    val healthEntries: StateFlow<List<String>> = _healthEntries

    fun addHealthEntry(entry: String) {
        _healthEntries.value += entry
    }
}

