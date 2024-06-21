
package com.example.quizapp01.ui.theme
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp01.ui.theme.data.Physics
import kotlinx.coroutines.delay

data class SubjectClassQuestions(val classes: String, val subject: String)
class QuizSetsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val subject = intent.getStringExtra("subject") ?: "DefaultSubject"
        val classes = intent.getStringExtra("class") ?: "DefaultClass"

        setContent {
            QuizApp01Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val subjectClassQuestions = SubjectClassQuestions(classes, subject)
                    QuizScreen(subjectClassQuestions)
                }
            }
        }
    }
}



@Composable
fun QuizScreen(subjectClassQuestions: SubjectClassQuestions) {


    var currentQuestion by remember { mutableIntStateOf(0) }
    var selectedOption by remember { mutableIntStateOf(-1) }
    var correctAnswers by remember { mutableIntStateOf(0) }
    var skippedQuestionCount by remember { mutableIntStateOf(0) }

var wrongAnswerCount by remember {
    mutableIntStateOf(0)
}
    var correctAnswersCount by remember { mutableIntStateOf(0) }

    val questions = Physics.getQuestions(subjectClassQuestions)

//    val questions = when (subjectClassQuestions) {
//        SubjectClassQuestions("Class 6", "Physics") -> listOf(
//            Question(
//                "What is the capital of France?",
//                listOf("Berlin", "Madrid", "Paris", "Rome"),
//                2
//            ),
//            Question(
//                "Which planet is known as the Red Planet?",
//                listOf("Earth", "Mars", "Venus", "Jupiter"),
//                1
//            ),
//            Question(
//                "What is the largest mammal in the world?",
//                listOf("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"),
//                1
//            ),
//            Question(
//                "Who wrote 'Romeo and Juliet'?",
//                listOf("Charles Dickens", "Jane Austen", "William Shakespeare", "Mark Twain"),
//                2
//            )
//        )
//        // Add more cases for other classes and subjects as needed
//        else -> emptyList()
//    }

    // Additional state for timer
    var timerRunning by remember { mutableStateOf(true) }
    var timeRemaining by remember { mutableLongStateOf(180) } // 10 minutes in milliseconds


//    Box(modifier = Modifier.fillMaxSize()) {
//        val image = painterResource(R.drawable.i003)
//        Image(
//            painter = image,
//            contentDescription = "<a href=\"https://www.freepik.com/free-vector/chemistry-icon_3887185.htm#fromView=image_search&track=&regularType=vector&page=1&position=51&uuid=f73f2340-a295-4018-8633-047b871c7454\">Image by macrovector</a> on Freepik",
//            contentScale = ContentScale.FillBounds,
//            modifier = Modifier.matchParentSize()
//
//        )



//    @Composable
//        fun CountdownTimer(boolean: Boolean = timerRunning) {
//
//
//
//
//            LaunchedEffect(key1 = timeRemaining) {
//                while (timeRemaining.toInt() != 0) {
//                    delay(1000)
//
//                    timeRemaining--
//
//
//                }
//
//
//            }
//            if (timerRunning) {
//                Surface(
//                    color = Color.Red, modifier = Modifier.padding(5.dp)
//
//                ) {
//
//                    Text(text = "Time remaining:  $timeRemaining s")
////                Text(text = "Time remaining:  $subject")
////                Text(text = "Time remaining:  $classes")
//
//                }
//
//            }
//
//            if (timeRemaining.toInt() == 0) {
//                timerRunning = false
//
//            }
//
//
//        }


    @Composable
    fun CountdownTimer( quizCompleted: Boolean) {

        // Helper function to format time in HH:mm:ss
        fun formatTime(seconds: Long): String {
            val hours = seconds / 3600
            val minutes = (seconds % 3600) / 60
            val remainingSeconds = seconds % 60
            return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
        }

        LaunchedEffect(key1 = timeRemaining) {
            while (timerRunning) {
                delay(1000)
                timeRemaining--

                if (timeRemaining.toInt()==0){
                    timerRunning=false
                }
            }


        }



        if (timerRunning && !quizCompleted) {
            Surface(
                color = Color.Red,
                modifier = Modifier.padding(5.dp)
            ) {
                Text(text = "Time Remaining:  ${formatTime(timeRemaining)}")
            }
        }
    }





//gradient

//    val side1 = 100
//        val side2 = 200
//        Brush.radialGradient(
//            listOf(Color.Red, Color.Green, Color.Blue),
//            center = Offset(side1 / 2.0f, side2 / 2.0f),
//            radius = side1 / 2.0f,
//            tileMode = TileMode.Repeated
//        )
//        val linear = Brush.linearGradient(listOf(Color.White, Color.Cyan))

    //gradient end



        if (currentQuestion < questions.size && timerRunning) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)

            ) {


                CountdownTimer(  currentQuestion == questions.size)
                Spacer(modifier = Modifier.height(16.dp))
                Text(

                    text = "${currentQuestion + 1}: ${questions[currentQuestion].text}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OptionsList(
                    options = questions[currentQuestion].options,
                    selectedOption = selectedOption,
                    onOptionSelected = { selectedOption = it }
                )
                Spacer(modifier = Modifier.height(16.dp))
                val context= LocalContext.current
                Button(

                    onClick = {
                        if (selectedOption>=-1) {
//                            Toast.makeText(context, "$selectedOption", Toast.LENGTH_SHORT).show()
                            // Check the answer and move to the next question

                            when (selectedOption) {
                                questions[currentQuestion].correctOption -> {

                                    correctAnswersCount++


                                    // Correct answer
                                    // You can add logic for scoring or feedback here
                                }
                                -1 -> {
                                    skippedQuestionCount++
                                }
                                else -> {
                                    wrongAnswerCount++
                                }
                            }
                            selectedOption =-1
                            currentQuestion++
                            if(currentQuestion==questions.size){
                                timerRunning=false
                            }
                        }
                    },
                    modifier = Modifier

                        .fillMaxWidth()

                ) {
                    Text(text = if (currentQuestion == questions.size - 1) "Finish"  else "Next")
                }
            }


        } else {

            QuizSummary(
                totalQuestions = questions.size,
                correctAnswersCount = correctAnswersCount,
                skippedQuestionCount=skippedQuestionCount,
                wrongAnswerCount=wrongAnswerCount,
                timeRemaining=timeRemaining.toLong()

//            onRestartQuiz = {
//                currentQuestion = 0
//                selectedOption = -1
//                correctAnswers = 0
//                timerRunning = true
//                timeRemaining = 10 // Reset timer to 10 minutes
//            }
            )

        }
    }


@Composable
fun OptionsList(options: List<String>, selectedOption: Int, onOptionSelected: (Int) -> Unit) {
    LazyColumn {
        items(options.size) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .selectable(
                        selected = selectedOption == index,
                        onClick = { onOptionSelected(index) }
                    )
//                    .background(
//                        color = if (selectedOption == index) Color.Cyan else Color.Transparent
//                    )
            ) {
                RadioButton(
                    selected = selectedOption == index,
                    onClick = { onOptionSelected(index) },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 8.dp)
                )
                Text(
                    text = options[index],
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }


    }
}

@Composable
fun QuizSummary(
    totalQuestions: Int,
    correctAnswersCount: Int,
    skippedQuestionCount:Int,
    wrongAnswerCount:Int,
    timeRemaining:Long

) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Quiz Summary",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 16.dp).align(alignment = Alignment.CenterHorizontally)
            , textDecoration = TextDecoration.Underline
        )
        Text(

            text = "Total Questions: $totalQuestions", fontWeight = Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Correct Answers: $correctAnswersCount", color = Color.Green,fontWeight = Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Questions Skipped: $skippedQuestionCount",color = Color.Blue,fontWeight = Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Wrong Answers: $wrongAnswerCount", color = Color.Red,fontWeight = Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Time taken: $timeRemaining seconds",fontWeight = Bold,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        val context= LocalContext.current
        Button(onClick =
        {
//val text ="kill"
            val intent=Intent(context,Home::class.java)
            context.startActivity(intent)
//            intent.putExtra("kill", text)
            (context as Activity  ).finish()

        }

            , modifier = Modifier.fillMaxWidth()
        )


        {
            Text("Return to Home")

        }
    }
}

data class Question(val text: String, val options: List<String>, val correctOption: Int)

@Preview(showBackground = true)
@Composable
fun QuizScreenPreview() {
    QuizApp01Theme {

    }
}
