package com.example.quizapp01.ui.theme

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
import com.example.quizapp01.ui.theme.ui.login.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(containerColor = Color.White) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column {
                ActionButtonsRow()
              //  Spacer(modifier = Modifier.height(6.dp))
                QuoteCarousel(
                    quotes = listOf(
                        "“The Best Brains of the nation may be found on the last benches of the classroom.”",
                        "“Education is the most powerful weapon which you can use to change the world.”",
                        "“The beautiful thing about learning is that no one can take it away from you.”"
                    )
                )
                Spacer(modifier = Modifier.height(7.dp))
                ScrollContent(innerPadding,navController)
            }
        }
//        }
//    }
    }
}
//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    val items = listOf("Home", "Performance", "Leaderboard")
//    val icons = listOf(Icons.Default.Home, Icons.Default.BarChart, Icons.Default.Leaderboard)
//    var selectedIndex by remember { mutableIntStateOf(0) }
//
//    BottomNavigation(
//        backgroundColor = Color.White,
//        modifier = Modifier.height(39.dp)
//    ) {
//        items.forEachIndexed { index, item ->
//            BottomNavigationItem(
//                icon = { Icon(icons[index], contentDescription = item) },
//                label = {
//                    Text(
//                        text = item,
//                        style = TextStyle(
//                            fontSize = 12.sp
//                        )
//                    )
//                },
//                selected = selectedIndex == index,
//                unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
//                onClick = {
//                    selectedIndex = index
//                    when (item) {
//                        "Home" -> navController.navigate(Screen.Home.route)
//                        "Performance" -> navController.navigate(Screen.Performance.route)
//                        "Leaderboard" -> navController.navigate(Screen.Leaderboard.route)
//                    }
//                }
//            )
//        }
//    }
//}
@Composable
fun ActionButtonsRow() {
    val studyUrl="https://drive.google.com/drive/folders/1JBgljZCWH45v8UO_ImRp-qMKFEU3qqPm?usp=sharing"
    val topperUrl="https://drive.google.com/drive/folders/1IeEsp8hKsuFInG4x2tarIcbIH84P1DgY?usp=sharing"
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(icon = Icons.Default.Description, text = "Study Material",studyUrl)
        ActionButton(icon = Icons.AutoMirrored.Filled.Notes, text = "Topper's Notes",topperUrl)
    }
}

@Composable
fun ActionButton(icon: ImageVector, text: String,url :String) {

    val context=LocalContext.current
    Card(

        shape = RoundedCornerShape(8.dp),
        //border = BorderStroke(1.dp, Color.Gray),
        modifier = Modifier,
           // .padding(10.dp)
          // .height(20.dp),
        elevation = CardDefaults.cardElevation(3.dp),

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color.White)
                .width(150.dp)
                .height(50.dp)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW,Uri.parse(url))
                    startActivity(context, intent, null)
                }
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(24.dp)
            , Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = text, style = TextStyle(fontSize = 16.sp
            , color = Color.Black))

        }
    }
}

@Composable
fun QuoteCarousel(quotes: List<String>) {
    var currentIndex by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // Change quote every 3 seconds
            currentIndex = (currentIndex + 1) % quotes.size
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = quotes[currentIndex],
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ScrollContent(innerPadding: PaddingValues,navController: NavController) {
    val chemistry = "https://i.ibb.co/m5WK17r/Chemistry.jpg"
    val physics = "https://i.ibb.co/yf8LjYg/Physics.jpg"
    val maths = "https://i.ibb.co/3NQDb0v/Maths.jpg"
    val biology = "https://i.ibb.co/vvLLBwf/Biology.jpg"

    val courses = listOf(
        Course(imageRes = physics, "Physics"),
        Course(imageRes = chemistry, "Chemistry"),
        Course(imageRes = maths, "Mathematics"),
        Course(imageRes = biology, "Biology")
    )
    Box(contentAlignment = Alignment.Center) {
        Text(
            text = "Test Your Ability",
            style = TextStyle(
                fontSize = 24.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                // .padding(start = 30.dp, top = 0.dp, end = 0.dp, bottom = 2.dp)
                .fillMaxWidth() // Ensure the text takes the full width available
            //  .align(alignment = Alignment.CenterHorizontally) // Center the text horizontally within the Box
        )
    }
    Spacer(modifier = Modifier.height(5.dp))


    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(7.dp),
        ) {
            items(courses) { course ->
                CourseCard(course = course,navController = navController)
            }
        }
    }
}

@Composable
fun CourseCard(course: Course,navController: NavController) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
            .clickable {
                navController.navigate(Screen.TestSetsScreen.route)
//                {
//                    popUpTo(0) {
//                       // inclusive = true
//                    }
    //            }
            },
     //   colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(data = course.imageRes)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        size(51) // Scale down the image to fit the required size
                    }).build(),
                contentDescription = "null",
                modifier = Modifier
                    .size(51.dp)
                    .clip(RoundedCornerShape(6.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = course.title)
        }
    }
}

data class Course(val imageRes: String, val title: String)
