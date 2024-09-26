package com.example.quizapp01.ui.theme.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TestSetsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(Color.Black),
            title = {
                Text("Take Test",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    )
                    },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(Screen.Home.route) }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White)
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
//        Text(
//            text = "Select an option",
//            style = TextStyle(fontSize = 18.sp),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )
        Spacer(modifier = Modifier.height(32.dp))

        ActionButton(
            text = "Attempt All Questions",
            onClick = {
                navController.navigate(Screen.McqTestScreen.route) {
                    popUpTo(0) {
                        //inclusive = true
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ActionButton(
            text = "Attempt 10 Questions",
            onClick = { /* Handle click */ }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ActionButton(
            text = "Sets",
            onClick = {
                navController.navigate(Screen.McqTestScreen.route) {
                    popUpTo(0) {
                        //inclusive = true
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ActionButton(
            text = "Practice",
            onClick = { /* Handle click */ }
        )

        Spacer(modifier = Modifier.weight(1f))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .clickable { /* Handle click */ },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = "Know how this works?",
//                style = TextStyle(fontSize = 16.sp)
//            )
//            Spacer(modifier = Modifier.weight(1f))
//            TextButton(onClick = { /* Handle click */ }) {
//                Text(
//                    text = "Watch",
//                    style = TextStyle(fontSize = 16.sp, color = Color.Blue)
//                )
   //         }
        }
    }
}

@Composable
fun ActionButton( text: String, onClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 3.dp,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = text,
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Medium)
            )

        }
    }
}
