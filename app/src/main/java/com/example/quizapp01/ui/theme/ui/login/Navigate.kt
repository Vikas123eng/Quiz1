package com.example.quizapp01.ui.theme.ui.login


sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object SignUp : Screen("signup")
    data object Userdata : Screen("userdata")
    data object Performance: Screen("performance")
    data object Leaderboard: Screen("leaderboard")
    data object MainScreen: Screen("mainscreen")
}

