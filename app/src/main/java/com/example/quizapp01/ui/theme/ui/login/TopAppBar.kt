package com.example.quizapp01.ui.theme.ui.common

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Class
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Share
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Size
import com.example.quizapp01.ui.theme.ActionButtonsRow
import com.example.quizapp01.ui.theme.QuoteCarousel
import com.example.quizapp01.ui.theme.ScrollContent
import com.example.quizapp01.ui.theme.ui.login.Screen
import com.google.android.gms.auth.api.signin.GoogleSignIn.getClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(navController: NavController) {
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
                modifier = Modifier.width(280.dp) // Set the drawer width
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
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(data = logo)
                                        .apply {
                                            crossfade(true)
                                            size(Size.ORIGINAL) // Scale down the image to fit the required size
                                        }
                                        .build(),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxSize().clip(CircleShape)
                                )
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Column {
                                Text(
                                    text = "Vikas Ravidas",
                                    style = TextStyle(color = Color.White, fontSize = 17.sp)
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
                        label = { Text(text = "Change Class", style = TextStyle(color = Color.Black)) },
                        selected = false,
                        icon = { Icon(imageVector = Icons.Default.Class, contentDescription = "Change Class", tint = Color.Black) },
                        onClick = {
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.Gray,
                            unselectedContainerColor = Color.White
                        ),
                        label = { Text(text = "Share App", style = TextStyle(color = Color.Black)) },
                        selected = false,
                        icon = { Icon(imageVector = Icons.Default.Share, contentDescription = "Share App", tint = Color.Black) },
                        onClick = {
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.Gray,
                            unselectedContainerColor = Color.White
                        ),
                        label = { Text(text = "Need Help", style = TextStyle(color = Color.Black)) },
                        selected = false,
                        icon = { Icon(imageVector = Icons.AutoMirrored.Filled.Help, contentDescription = "Need Help", tint = Color.Black) },
                        onClick = {
                            scope.launch { drawerState.close() }
                        }
                    )
                    NavigationDrawerItem(
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = Color.Gray,
                            unselectedContainerColor = Color.White
                        ),
                        label = { Text(text = "Logout", style = TextStyle(color = Color.Black)) },
                        selected = false,
                        icon = { Icon(imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = "Logout", tint = Color.Black) },
                        onClick = {
                            Firebase.auth.signOut()
                            googleSignInClient.signOut().addOnCompleteListener {
                                Toast.makeText(context, "Logging out...", Toast.LENGTH_SHORT).show()
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
                    colors = topAppBarColors(containerColor = Color.White),
                    title = {
                        Text(text = "App of War", style = TextStyle(color = Color.Black, fontSize = 20.sp))
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu", modifier = Modifier.size(30.dp), Color.Black)
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            Toast.makeText(context, "No new notifications", Toast.LENGTH_SHORT).show()
                        }) {
                            Icon(imageVector = Icons.Default.Notifications, contentDescription = "Notifications", modifier = Modifier.size(30.dp), Color.Black)
                        }
                    }
                )
            }
        ) { innerPadding ->
            ScrollContent(innerPadding)
        }
    }
}
