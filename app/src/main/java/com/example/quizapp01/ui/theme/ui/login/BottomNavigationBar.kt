package com.example.quizapp01.ui.theme.ui.login

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Leaderboard
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import java.util.Locale


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf("Home", "Performance", "Leaderboard")
    val icons = listOf(Icons.Default.Home, Icons.Default.BarChart, Icons.Default.Leaderboard)
    var selectedIndex by remember { mutableIntStateOf(0) }

    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(56.dp)
    ) {

        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item)
                       },
                label = {
                    Text(
                        text = item,
                        style = TextStyle(
                            fontSize = 12.sp,
                           // color = Color.Black
                        )
                    )
                },
                selected = selectedIndex == index,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Black
                ),

//                selectedContentColor = LocalContentColor.current,
//              unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
                onClick = {
                    selectedIndex = index
                    when (item) {
                        "Home" -> navController.navigate(Screen.Home.route)
                        {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        "Performance" -> navController.navigate(Screen.Performance.route)
                        {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        "Leaderboard" -> navController.navigate(Screen.Leaderboard.route)
                        {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}


//@Composable
//fun BottomNavigationBar(navController: NavController) {
//    val items = listOf(Screen.Home, Screen.Performance, Screen.Leaderboard)
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentRoute = navBackStackEntry?.destination?.route
//
//    BottomAppBar {
//        BottomNavigation(
//            backgroundColor = Color.White,
//            modifier = Modifier.height(39.dp)) {
////            items.forEach { screen ->
////                BottomNavigationItem(
////                    icon = {
////                        when (items) {
////                            listOf(Screen.Home) -> Icon(imageVector = Icons.Default.Home, contentDescription = "Home")
////                            listOf(Screen.Performance) -> Icon(imageVector = Icons.Default.Star, contentDescription = "Performance")
////                            listOf(Screen.Leaderboard) -> Icon(imageVector = Icons.Default.Leaderboard, contentDescription = "Leaderboard")
////
////                        }
////
////                    },
////                    label = { Text(screen.route.replaceFirstChar {
////                        if (it.isLowerCase()) it.titlecase(
////                            Locale.getDefault()
////                        ) else it.toString()
////                    }) },
////                    selected = currentRoute == screen.route,
////                    onClick = {
////                        if (currentRoute != screen.route) {
////                            navController.navigate(screen.route) {
////                                popUpTo(navController.graph.startDestinationId)
////                                launchSingleTop = true
////                            }
////                        }
////                    }
////                )
////            }
//        }
//    }
//}