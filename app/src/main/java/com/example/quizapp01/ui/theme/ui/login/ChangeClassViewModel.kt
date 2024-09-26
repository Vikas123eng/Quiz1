package com.example.quizapp01.ui.theme.ui.login

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "class_selection")

private val SELECTED_CLASS_KEY = intPreferencesKey("selected_class")

class ClassSelectionRepository(private val context: Context) {

    private val defaultClassLevel = ClassSelection.TENTH.gradeLevel

    val selectedClassFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            // Use defaultClassLevel only if no value is set
            preferences[SELECTED_CLASS_KEY] ?: defaultClassLevel
        }

    suspend fun initializeDefaultClassIfNeeded() {
        context.dataStore.edit { preferences ->
            if (!preferences.contains(SELECTED_CLASS_KEY)) {
                preferences[SELECTED_CLASS_KEY] = defaultClassLevel
            }
        }
    }

    suspend fun updateClass(newClass: Int) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CLASS_KEY] = newClass
        }
    }
}


class ClassSelectionViewModel(private val repository: ClassSelectionRepository) : ViewModel() {

    val selectedClassFlow: Flow<Int> = repository.selectedClassFlow

    init {
        viewModelScope.launch {
            repository.initializeDefaultClassIfNeeded()
        }
    }

    suspend fun updateClass(newClass: Int) {
        repository.updateClass(newClass)
    }
}
class ClassSelectionViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClassSelectionViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClassSelectionViewModel(ClassSelectionRepository(context)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}