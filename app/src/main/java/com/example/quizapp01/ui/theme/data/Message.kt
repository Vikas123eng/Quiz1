package com.example.quizapp01.ui.theme.data

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.quizapp01.ui.theme.QuizApp01Theme
import com.example.quizapp01.ui.theme.QuizSetsActivity


class MessageActivity : ComponentActivity() {
//    data class Message(
//        val Class6: String = "Class6",
//        val Class7: String = "Class7",
//        val Class8: String = "Class8",
//        val Class9: String = "Class9"
//
//    )







    data class SchoolClass(val className: String)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val subject = intent.getStringExtra("subject") ?: "DefaultSubject"
        setContent {
            QuizApp01Theme {

                // A surface container using the 'background' color from the theme
                Surface(

                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {



                    val classes = listOf(
                        SchoolClass("Class 6"),
                        SchoolClass("Class 7"),
                        SchoolClass("Class 8"),
                        SchoolClass("Class 9"),
                        SchoolClass("Class 10"))
                    // Call the LoginScreen composable



                    ClassList(classes = classes, modifier = Modifier, selectedSubject =subject)
                }
            }
        }
    }



    @Composable

    fun ClassList(classes: List<SchoolClass>, modifier: Modifier, selectedSubject: String)
    {


            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(classes) { schoolClass ->
                    Card(

                        modifier = Modifier
                            .background(Color.Red)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clickable { /* Handle c lick action */

                                val intent =
                                    Intent(this@MessageActivity, QuizSetsActivity::class.java)
                                intent.putExtra("subject", selectedSubject)
                                intent.putExtra("class", schoolClass.className)
                                startActivity(intent)


                            },
                        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),

//
                    )


                    {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                                .background(Color.White)



                        ) {

                            val subject = intent.getStringExtra("subject") ?: "DefaultSubject"
                            Text(
                                text = subject,
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = Color.Red
                            )



                            Text(
                                text = schoolClass.className,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.primary

                            )
//                        Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Take test",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                }
            }
        }


//    @Preview
//    @Composable
//    fun ClassListPreview() {
//        val classes = listOf(
//            SchoolClass("Class 6"),
//            SchoolClass("Class 7"),
//            SchoolClass("Class 8"),
//            SchoolClass("Class 9"),
//            SchoolClass("Class 10")
//        )
//
//        ClassList(classes = classes, modifier = Modifier)
//
//    }

//@Composable
//fun MessageList(messages: List<SchoolClass>) {
//    Column {
//        messages.forEach { message ->
//            MessageRow(messages)
//        }
//    }
//}

}



//data class QuestionSet(
//    val subject: String = "",
//    val className: String = "",
//    val questions: List<Question> = emptyList()
//)

data class Question(
    val text: String = "",
    val options: List<String> = emptyList(),
    val correctAnswer: String = ""
)