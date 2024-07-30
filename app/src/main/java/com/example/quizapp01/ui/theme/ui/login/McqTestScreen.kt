package com.example.quizapp01.ui.theme.ui.login


import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestore.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.Description
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.android.gms.auth.api.signin.GoogleSignIn.*
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.*
import kotlinx.coroutines.delay
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.example.quizapp01.ui.theme.HomeScreen
import com.example.quizapp01.ui.theme.Lime_Green
import com.example.quizapp01.ui.theme.UI_color
import com.example.quizapp01.ui.theme.Vibrant_Orange
import com.example.quizapp01.ui.theme.ui.login.Screen
import kotlinx.coroutines.selects.whileSelect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


val db: FirebaseFirestore
    get() = getInstance()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun McqTestScreen(viewModel: QuizViewModel = viewModel(), navController: NavController) {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }
    var backPressCount by remember {mutableIntStateOf(0)}
    val state by viewModel.state.collectAsState()
    val navigateToScoreCard by viewModel.navigateToScoreCard.observeAsState()
    val navigateToSolutions by viewModel.navigateToSolutions.observeAsState()
    val timeLeft = (viewModel.totalTime - state.elapsedTime).coerceAtLeast(0L)


    val context = LocalContext.current
    BackHandler(enabled = true,onBack = {
        backPressCount++

        if(backPressCount==1)
        {
            Toast.makeText(context,"Press again to exit ",Toast.LENGTH_SHORT).show()

        }
        else{
            (context as? Activity)?.finish()
        }
    })
//    {
//        // Exit the app when back is pressed on the Home screen
////
//       navController.navigate(Screen.MainScreen.route)
//       {
//           popUpTo(navController.graph.startDestinationId)
//           {
//               inclusive=true
//           }
//           launchSingleTop = true
//       }
//    }

    LaunchedEffect(Unit) {
        viewModel.startQuiz(navController)
    }

    LaunchedEffect(navigateToScoreCard) {
        navigateToScoreCard?.getContentIfNotHandled()?.let { (score, totalQuestions) ->
            navController.navigate(Screen.ScoreCard.createRoute(score, totalQuestions))
        }
    }

//    LaunchedEffect(navigateToSolutions) {
//        navigateToSolutions?.getContentIfNotHandled()?.let { (questions, answers) ->
//            navController.navigate(Screen.Solutions.createRoute(questions, answers))
//        }
//    }

    Scaffold(
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Text("Question ${state.currentQuestionIndex + 1}/${state.questions.size}")
                },
                actions = {
                    Text("Time left: ${timeLeft / 1000} seconds")
                }
            )
        }
    ) { padding ->
        if (state.questions.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(3.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = state.questions[state.currentQuestionIndex].text,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(23.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    state.questions[state.currentQuestionIndex].options.forEach { (key, value) ->
                        val isSelected = selectedAnswer == key
                        androidx.compose.material.Card(
                            shape = RoundedCornerShape(8.dp),
                            elevation = 4.dp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    selectedAnswer = key
                                },
                            backgroundColor = if (isSelected) UI_color else Color.White
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(35.dp)
                            ) {
                                Text(value)
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (state.currentQuestionIndex < state.questions.size - 1) {
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .height(43.dp)
                                    .width(80.dp)
                                    .clickable {
                                        selectedAnswer?.let {
                                            viewModel.submitAnswer(it)
                                            selectedAnswer = null
                                        }
                                        viewModel.nextQuestion()
                                    },
                                colors = CardDefaults.cardColors(Color.White),
                                elevation = CardDefaults.cardElevation(3.dp),
                            ) {
                                Text(
                                    "Next",
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(9.dp),
                                            style = TextStyle (
                                            textAlign = TextAlign.Center,
                                    color = Color.Blue,
                                    fontWeight = FontWeight.Bold,
                                                fontSize = 20.sp
                                )
                                )
                            }
                        } else {
                            Card(
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .height(43.dp)
                                    .width(80.dp)
                                    .clickable {
                                        selectedAnswer?.let {
                                            viewModel.submitAnswer(it)
                                            selectedAnswer = null
                                        }
                                        val score = viewModel.state.value.score
                                        val totalQuestions = viewModel.state.value.questions.size
                                        val solutionsJson =
                                            Json.encodeToString(viewModel.state.value.answers)
                                        val questionsJson =
                                            Json.encodeToString(viewModel.state.value.questions)
                                        Log.d("questionsJson", questionsJson)
//                                        navController.navigate(Screen.ResultScreen.createRoute(score, totalQuestions, solutionsJson,questionsJson)) {
//                                            popUpTo(navController.graph.startDestinationId) {
//                                                inclusive = true
//                                            }
//                                            launchSingleTop=true
//                                        }
                                    },
                                colors = CardDefaults.cardColors(Color.White),
                                elevation = CardDefaults.cardElevation(3.dp),
                            ) {
                                Text(
                                    "Submit",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(9.dp),
                                    style = TextStyle(
                                        textAlign = TextAlign.Center,
                                        color = Color.Blue,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 19.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}

