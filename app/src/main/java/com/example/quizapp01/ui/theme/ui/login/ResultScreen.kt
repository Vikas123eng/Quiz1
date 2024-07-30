package com.example.quizapp01.ui.theme.ui.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

//
//@SuppressLint("StateFlowValueCalledInComposition")
//@Composable
//fun ResultScreen(
//    navController: NavController,
//    viewModel: QuizViewModel = viewModel(),
//    score: Int,
//    totalQuestions: Int,
//    solutionsJson: String,
//    questionsJson: String
//) {
//    val solutions = remember {
//        Json.decodeFromString<List<Pair<String, Boolean>>>(solutionsJson)
//
//    }
//    Log.d("questionsJson", questionsJson.toString())
//    val questions = remember {
//        Json.decodeFromString<List<Pair<String, Boolean>>>(questionsJson)
//
//    }
//    Log.d("questions", questions.toString())
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//            .padding(16.dp)
//    ) {
//        // Display ScoreCard
//        SolutionsScreen(
//            questions = questions,
//            answers = solutions
//        )
//        ScorecardScreen(score = score, totalQuestions = totalQuestions,navController)
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        // Display Solutions
//
//    }
//}
