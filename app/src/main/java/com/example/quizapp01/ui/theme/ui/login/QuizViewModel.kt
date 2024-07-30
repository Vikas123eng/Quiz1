package com.example.quizapp01.ui.theme.ui.login
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
data class Question(val text: String, val options: Map<String, String>, val correctOption: String)

 data class QuizState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val elapsedTime: Long = 0L,
    val answers: List<Pair<String, Boolean>> = emptyList(), // Pair of selected answer and correctness
    val score: Int = 0
)


class QuizViewModel : ViewModel() {
    private val db = FirebaseFirestore.getInstance()
    private var totalTimePerQuestion = 90_000L
    private var timerJob: Job? = null

    private val _state = MutableStateFlow(QuizState())
    val state: StateFlow<QuizState> = _state

    private val _navigateToScoreCard = MutableLiveData<Event<Pair<Int, Int>>>()
    val navigateToScoreCard: LiveData<Event<Pair<Int, Int>>> = _navigateToScoreCard

    private val _navigateToSolutions = MutableLiveData<Event<String>>()
    val navigateToSolutions: LiveData<Event<String>> = _navigateToSolutions

    var totalTime: Long = 0L
        private set

    fun startQuiz(navController: NavController) {
        db.collection("classes").document("class 9")
            .collection("subjects").document("physics").collection("questions")
            .get().addOnSuccessListener { result ->
                val questions = result.map { document ->
                    val questionText = document.getString("questionText")!!
                    val options = document.get("options") as Map<String, String>
                    val correctAnswer = document.getString("correctAnswer")!!
                    Question(questionText, options, correctAnswer)
                }
                _state.value = _state.value.copy(questions = questions)
                // Calculate total time based on the number of questions
                totalTime = totalTimePerQuestion * questions.size
                startTimer(navController)
            }
    }

    private fun startTimer(navController: NavController) {
        timerJob = viewModelScope.launch {
            while (_state.value.elapsedTime < totalTime) {
                delay(1000L)
                _state.value = _state.value.copy(
                    elapsedTime = _state.value.elapsedTime + 1000L
                )
            }
        }
    }

    fun submitAnswer(answer: String) {
        val currentQuestion = _state.value.questions[_state.value.currentQuestionIndex]
        val isCorrect = currentQuestion.correctOption == answer
        val newAnswers = _state.value.answers + Pair(answer, isCorrect)
        val newScore = if (isCorrect) _state.value.score + 1 else _state.value.score
        _state.value = _state.value.copy(
            answers = newAnswers,
            score = newScore
        )
        nextQuestion()
    }

    fun nextQuestion() {
        if (_state.value.currentQuestionIndex < _state.value.questions.size - 1) {
            _state.value = _state.value.copy(
                currentQuestionIndex = _state.value.currentQuestionIndex + 1
            )
        } else {
            // Quiz finished
            timerJob?.cancel()
            //_navigateToScoreCard.value = Event(Pair(_state.value.score, _state.value.questions.size))
        }
    }
}
//
//@Composable
//fun ScorecardScreen(score: Int, totalQuestions: Int) {
//    Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center,
//        modifier = Modifier.fillMaxSize()
//    ) {
//        Text("Scorecard", fontSize = 24.sp, fontWeight = FontWeight.Bold)
//        Spacer(modifier = Modifier.height(16.dp))
//        Text("Total Marks: $score/${totalQuestions * 4}") // Assuming each question is 4 marks
//    }
//}
//
//
//@Composable
//fun SolutionsScreen(questions: List<Question>, answers: List<Pair<String, Boolean>>) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        questions.forEachIndexed { index, question ->
//            val answerPair = answers.getOrNull(index)
//            val selectedAnswer = answerPair?.first
//            val isCorrect = answerPair?.second ?: false
//            val color = if (isCorrect) Color.Green else Color.Red
//
//            Column(modifier = Modifier.padding(vertical = 8.dp)) {
//                Text(question.text, fontSize = 16.sp, fontWeight = FontWeight.Bold)
//                Spacer(modifier = Modifier.height(8.dp))
//                question.options.forEach { (key, value) ->
//                    Text(
//                        text = value,
//                        color = if (selectedAnswer == key) color else Color.Black,
//                        fontWeight = if (selectedAnswer == key) FontWeight.Bold else FontWeight.Normal
//                    )
//                }
//            }
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//    }
//}