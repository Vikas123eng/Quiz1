package com.example.quizapp01.ui.theme

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.quizapp01.R
import com.example.quizapp01.ui.theme.data.MessageActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class Home : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {






        super.onCreate(savedInstanceState)


        setContent {
            QuizApp01Theme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Call the LoginScreen composable
                    ScaffoldExample()
                }
            }
        }


    }
    

}




    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldExample() {

        var presses by remember { mutableIntStateOf(0) }
        val context= LocalContext.current


        Scaffold(
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {

                        Text(text = "Home")
                    },navigationIcon = {
                        IconButton(onClick = { /* do something */


                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Localized description",
                                        modifier = Modifier.fillMaxSize()
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {Firebase.auth.signOut()
                            Toast.makeText(context, "logging out", Toast.LENGTH_SHORT).show()
                            val intent = Intent(context, LoginActivity::class.java)
                            context.startActivity(intent)
                            (context as Activity).finish()

                        }) {
                            Icon(
                                imageVector = Icons.Filled.Logout,
                                contentDescription = "Localized description",
                                        modifier = Modifier.fillMaxSize()
                            )
                        }
                    }

                )

            }

//
        ) { innerPadding ->
            ScrollContent(innerPadding)
        }
    }


@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    Box(modifier = Modifier.fillMaxSize()) {
        val image = painterResource(R.drawable.i002)
        Image(painter = image,
            contentDescription = "<a href=\"https://www.freepik.com/free-vector/chemistry-icon_3887185.htm#fromView=image_search&track=&regularType=vector&page=1&position=51&uuid=f73f2340-a295-4018-8633-047b871c7454\">Image by macrovector</a> on Freepik"
            , contentScale = ContentScale.FillBounds
            , modifier=Modifier.matchParentSize()

        )
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            val context = LocalContext.current
            val intent = Intent(context, MessageActivity::class.java)
            val subject = intent.getStringExtra("subject") ?: "DefaultSubject"
//            val kill = intent.getStringExtra("kill") ?: "Not kill"

            // Call the ClassList composable with the selected subject
            ClassList()
        }
    }
}

@Composable
fun ButtonItem(text: String) {
    val context= LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, MessageActivity::class.java)
            intent.putExtra("subject", text)
            context.startActivity(intent)
            // Handle button click
        },
        modifier = Modifier


            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = text)
    }
}




@Preview
@Composable
fun HomePreview(){
    ScaffoldExample()
}

@Composable
fun ClassList() {
    // Create a list of subjects
    val subjects = listOf("Physics", "Chemistry", "Maths", "Biology")

    // Display the subjects using LazyColumn
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        items(subjects) { subject ->
            // Display each subject in a Card or any other layout as needed
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
            ) {
                ButtonItem(text = subject)
            }
        }
    }
}

