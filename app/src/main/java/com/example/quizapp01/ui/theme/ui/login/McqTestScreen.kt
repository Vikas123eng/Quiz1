package com.example.quizapp01.ui.theme.ui.login


//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quizapp01.ui.theme.UI_color
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestore.getInstance
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
                colors = TopAppBarDefaults.topAppBarColors(Color.Black),
                title = {
                    Text(
                        "Question ${state.currentQuestionIndex + 1}/${state.questions.size}",
                        style = TextStyle(
                            color = Color.Green,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        )
                },
                actions = {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(style = SpanStyle(color = Color.Yellow)) {append("Time left: ")
                            }
                            withStyle(style = SpanStyle(
                                color = if (timeLeft / 1000 < 30) Color.Red else Color.White,
                                fontWeight = FontWeight.Bold
                            )) {
                                append("${timeLeft / 1000}")
                            }
                            withStyle(style = SpanStyle(color = Color.White)){
                                append(" seconds")
                            }
                        },
                        fontSize = 16.sp
                    )
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
                                            navController.navigate(Screen.Home.route)
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

