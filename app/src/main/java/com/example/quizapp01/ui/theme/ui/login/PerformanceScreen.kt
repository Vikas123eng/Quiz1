package com.example.quizapp01.ui.theme.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quizapp01.R
import com.example.quizapp01.ui.theme.Coral_Pink

@Composable
fun PerformanceScreen(navController: NavController) {
    val subjects = listOf(
        SubjectData("Physics", R.drawable.physics, Color.Green,testAttempt=12),
        SubjectData("Chemistry", R.drawable.chemistry, Coral_Pink,testAttempt=1),
        SubjectData("Maths", R.drawable.mathematics, Color(0xFFFFBB00),testAttempt=2),
        SubjectData("Biology", R.drawable.biology, Color.Red,testAttempt=4)
    )

    val setsNum=listOf(
        HistoryData(setsNum = 1),
        HistoryData(setsNum = 2),
        HistoryData(setsNum = 3),
        HistoryData(setsNum = 4),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

            //Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Tests Analysis",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            )

        Spacer(modifier = Modifier.height(12.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            //.wrapContentHeight(Alignment.CenterVertically)
            .background(color = Color(0xFFE8DEF8).copy(.5f))
            //.clip(RoundedCornerShape(8.dp))
          //  .padding(16.dp)
            )
                {
                    Spacer(modifier = Modifier.height(20.dp))
        subjects.chunked(2).forEach { rowSubjects ->
            Row {
                Spacer(modifier = Modifier.width(15.dp))
                rowSubjects.forEach { subject ->
                    SubjectCard(subject)
                    Spacer(modifier = Modifier.width(60.dp))
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }

//HorizontalDivider()

        Spacer(modifier = Modifier.height(40.dp))


       // Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = "History",
            style = TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        val listState = rememberLazyListState()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                // .padding(16.dp)
                .background(Color.White), // Set background color here
            state = listState // If you intend to use listState
        ) {

            item(setsNum) { // No need for item(1) here
                Spacer(modifier = Modifier.width(23.dp))
                setsNum.forEach { history ->
                    HistoryCard(history)
                    Spacer(modifier = Modifier.height(6.dp))
                }


            }
        }
}

}



@Composable
fun SubjectCard(subject: SubjectData) {
    Card(
        Modifier.size(120.dp, 100.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
        //border = BorderStroke(1.dp, Color.Black)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color.White)
        ) {
            Spacer(modifier = Modifier.height(9.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                Image(
                    painter = painterResource(subject.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .border(1.dp, Color.Transparent, CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                Text(
                    text = "${subject.testAttempt}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), Arrangement.Center) {
                Text(
                    text = "Tests Attempted",
                    style = TextStyle(color = Color.Black, fontSize = 10.sp)
                )
            }
        }
    }

}

@Composable
fun HistoryCard(history: HistoryData) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
//                {
//                    popUpTo(0) {
//                       // inclusive = true
//                    }
                //            }
            },
           colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {

            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Set ${history.setsNum}")
        }
    }

}


data class HistoryData(
    val setsNum: Int
)


data class SubjectData(
    val name: String,
    val imageRes: Int,
    val borderColor: Color,
    val testAttempt: Int
)
