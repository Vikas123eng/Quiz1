package com.example.quizapp01.ui.theme.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quizapp01.ui.theme.Lime_Green
import kotlinx.coroutines.launch


enum class ClassSelection(val gradeLevel: Int) {
    NINTH(9),
    TENTH(10)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun ChangeClassScreen(
    navController: NavController,
    // viewModel: ClassSelectionViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val viewModel: ClassSelectionViewModel = viewModel(factory = ClassSelectionViewModelFactory(context))
    var currentClass by remember { mutableIntStateOf(ClassSelection.TENTH.gradeLevel) }
    var expanded by remember { mutableStateOf(false) }
    var selectedClass by remember { mutableIntStateOf(currentClass) } // Start with Class 10
    val coroutineScope = rememberCoroutineScope()


    LaunchedEffect(key1 = Unit) {// Launch a coroutine once
        viewModel.selectedClassFlow.collect { value ->
            currentClass = value // Update currentClass with the value from DataStore
        }
    }



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(Color.Black),
            title = {
                Text("Change Class",
                    style = TextStyle(color = Color.White,
                                      fontSize = 22.sp,
                                      fontWeight = FontWeight.Bold
                    ))
                    },
            navigationIcon = {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back",tint = Color.White)
                }
            }
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
                onClick = { expanded = true }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Selected Class: $selectedClass",
                        style= androidx.compose.ui.text.TextStyle(color = Lime_Green,
                                                                    fontSize = 24.sp,
                                                                    fontWeight = FontWeight.Bold,
                                                                    fontFamily= androidx.compose.ui.text.font.FontFamily.Cursive
                                                                    ) )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "click to change class",
                        style= androidx.compose.ui.text.TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Cursive)
                        )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color.Red), // Set dropdown background to white
                       offset = androidx.compose.ui.unit.DpOffset(70.dp, 18.dp)
                    ) {
                        ClassSelection.entries.forEach { classSelection ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedClass = classSelection.gradeLevel
                                    coroutineScope.launch {
                                        viewModel.updateClass(selectedClass)
                                    }
                                    expanded = false
                                }

                            ) {
                                Text(text = "Class -> IX", color = Color.White,
                                    style= androidx.compose.ui.text.TextStyle(
                                        fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}