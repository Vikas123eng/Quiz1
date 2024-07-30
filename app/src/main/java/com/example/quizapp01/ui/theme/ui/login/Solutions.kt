package com.example.quizapp01.ui.theme.ui.login


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SolutionsScreen(questions: List<Question>, answers: List<Pair<String, Boolean>>) {
    Column(
        modifier = Modifier
           // .fillMaxSize()
            .padding(16.dp)
    ) {

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
    }
}

