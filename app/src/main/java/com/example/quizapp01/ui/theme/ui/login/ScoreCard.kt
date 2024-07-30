package com.example.quizapp01.ui.theme.ui.login

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun ScorecardScreen(score: Int, totalQuestions: Int,navController: NavController) {
//    val nestedNavController = rememberNavController()
//    val navBackStackEntry by nestedNavController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//    val context = LocalContext.current
//
//    BackHandler(enabled = currentRoute == Screen.Home.route) {
//        // Exit the app when back is pressed on the Home screen
//      navController.navigate(Screen.MainScreen.route)
//    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
       // modifier = Modifier.fillMaxSize()
    ) {
        Text("Scorecard", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Total Marks: $score/${totalQuestions }") // Assuming each question is 4 marks
    }
}
