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
    data object ChangeClass: Screen("changeclass")
  data object TestSetsScreen: Screen("testsets")
    data object McqTestScreen: Screen("mcqtest")
//    data object ResultScreen : Screen("result_screen/{score}/{totalQuestions}/{solutions}/{questions}") {
//        fun createRoute(score: Int, totalQuestions: Int, solutions: String,questions: String) = "result_screen/$score/$totalQuestions/$solutions/$questions"
//    }
//    data object Solutions: Screen("solutions")
////    data object ScoreCard: Screen("scorecard")
data object ScoreCard : Screen("scoreCard/{score}/{totalQuestions}") {
        fun createRoute(score: Int, totalQuestions: Int) = "scoreCard/$score/$totalQuestions"
    }
    data object Solutions : Screen("solutions/{questions}/{answers}") {
        fun createRoute(questions: String, answers: String) = "solutions/$questions/$answers"
    }
}

