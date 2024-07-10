package com.example.quizapp01.ui.theme

import android.graphics.fonts.FontStyle
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.example.quizapp01.R
import com.example.quizapp01.ui.theme.ui.login.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn.*
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current
    val logo = "https://i.ibb.co/8N8n4th/Design12.jpg"
    val scope = rememberCoroutineScope()
    val googleSignInClient = getClient(context, DEFAULT_SIGN_IN)
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)


    ModalNavigationDrawer(

        gesturesEnabled = true,
        drawerState = drawerState,
        drawerContent = {

            ModalDrawerSheet(
                drawerContainerColor = Color.White,
                modifier = Modifier
                    .width(280.dp)
            // Set the drawer width
            ) {

                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black)
                            .height(159.dp)
                            .padding(12.dp),
                        contentAlignment = Alignment.TopStart
                    ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .border(
                                        BorderStroke(3.dp, Color.White),
                                        CircleShape
                                    )
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current).data(data = logo)
                                        .apply(block = fun ImageRequest.Builder.() {
                                            crossfade(true)
                                            size(Size.ORIGINAL) // Scale down the image to fit the required size
                                        }).build(),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(CircleShape)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Column {
                                Text(
                                    text = "Vikas Ravidas",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 17.sp
                                    )
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "vikasravidas789@gmail.com",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontSize = 14.sp,
                                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                                    )
                                )
                            }
                        }
                    }

                    HorizontalDivider()



                        NavigationDrawerItem(
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color.Gray,
                                unselectedContainerColor = Color.White
                            ),
                            label = { Text(text = "Home",style = TextStyle(color = Color.Black)) },
                            selected = false,
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.Home,
                                    contentDescription = "Home",
                                    tint = Color.Black
                                    
                                )
                            },
                            onClick = {
                                scope.launch {
                                    drawerState.close()
                                }
                            }
                        )
                        NavigationDrawerItem(
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = Color.Gray,
                                unselectedContainerColor = Color.White
                            ),
                            label = { Text(text = "Logout", style = TextStyle(color = Color.Black)) },
                            selected = false,
                            icon = {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.Logout,
                                    contentDescription = "Logout",
                                   tint = Color.Black
                                )
                            },
                            onClick = {
                                Firebase.auth.signOut()
                                googleSignInClient.signOut().addOnCompleteListener {
                                    Toast.makeText(context, "Logging out...", Toast.LENGTH_SHORT)
                                        .show()
                                    navController.navigate(Screen.Login.route)
                                }
                            }
                        )

                }

        }
}
    ) {
        Scaffold(
            containerColor = Color.White,
            topBar = {
                TopAppBar(
                    colors = topAppBarColors(
                        containerColor = Color.White,
                    ),
                    title = {
                        Text(
                            text = "App of War",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 20.sp
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Filled.Menu,
                                contentDescription = "Menu",
                                modifier = Modifier.size(30.dp),
                                Color.Black
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            Toast.makeText(context, "No new notifications", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                modifier = Modifier.size(30.dp),
                                Color.Black
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
            ) {
                Column {
                    ActionButtonsRow()
                        //Spacer(modifier = Modifier.height(6.dp))
                    QuoteCarousel(
                        quotes = listOf(
                            "“The Best Brains of the nation may be found on the last benches of the classroom.”",
                            "“Education is the most powerful weapon which you can use to change the world.”",
                            "“The beautiful thing about learning is that no one can take it away from you.”"
                        )
                    )
                    ScrollContent(innerPadding)
                }
            }
        }
    }
}

@Composable
fun ActionButtonsRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ActionButton(icon = Icons.Default.Description, text = "Study Material")
        ActionButton(icon = Icons.AutoMirrored.Filled.Notes, text = "Topper's Notes")
    }
}

@Composable
fun ActionButton(icon: ImageVector, text: String) {
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
fun ScrollContent(innerPadding: PaddingValues) {
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

    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Test You Ability", style = TextStyle(fontSize = 24.sp, color = Color.Black,
            fontWeight = FontWeight.Bold),
            modifier = Modifier.align(Alignment.TopCenter).padding(30.dp)
        )
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(courses) { course ->
                CourseCard(course = course)
            }
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier.fillMaxWidth()
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
                    .size(50.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = course.title)
        }
    }
}

data class Course(val imageRes: String, val title: String)
