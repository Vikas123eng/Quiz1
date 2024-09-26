package com.example.quizapp01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp01.ui.theme.HomeScreen
import com.example.quizapp01.ui.theme.LoginScreen
import com.example.quizapp01.ui.theme.QuizApp01Theme
import com.example.quizapp01.ui.theme.SignUpScreen
import com.example.quizapp01.ui.theme.SplashScreen
import com.example.quizapp01.ui.theme.ui.login.ChangeClassScreen
import com.example.quizapp01.ui.theme.ui.login.LeaderboardScreen
import com.example.quizapp01.ui.theme.ui.login.MainScreen
import com.example.quizapp01.ui.theme.ui.login.PerformanceScreen
import com.example.quizapp01.ui.theme.ui.login.Screen
import com.example.quizapp01.ui.theme.ui.login.TestSetsScreen
import com.example.quizapp01.ui.theme.ui.login.UserDataScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn.getClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase



class MainActivity : ComponentActivity() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient

//    private fun setUpFirestore() {
//        val firestore = FirebaseFirestore.getInstance()
//        val collectionReference: CollectionReference = firestore.collection("classes")
//
//        collectionReference.addSnapshotListener { value, error ->
//            if (error != null) {
//                Log.e("Firestore Error", "Error retrieving data", error)
//                Toast.makeText(this, "Error: ${error.message}", Toast.LENGTH_SHORT).show()
//                return@addSnapshotListener
//            }
//
//            if (value != null) {
//                if (value.isEmpty) {
//                    Log.d("Firestore Data", "No documents found in the 'classes' collection.")
//                    Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show()
//                } else {
//                    val quizzes = value.toObjects(Quiz::class.java)
//                    Log.d("Data", quizzes.toString())
//                }
//            } else {
//                Log.d("Firestore Data", "Snapshot is null")
//                Toast.makeText(this, "Data is null", Toast.LENGTH_SHORT).show()
//            }
//        }
  //  }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val auth: FirebaseAuth?
        auth = Firebase.auth
        val currentUser = auth.currentUser
        val gso = Builder(DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = getClient(this, gso)
        setContent {
            QuizApp01Theme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screen.Splash.route) {
                    composable(Screen.Splash.route) {
                        SplashScreen(onTimeOut = {
                            if (currentUser != null) {
                                navController.navigate(Screen.MainScreen.route)
                            } else {
                                navController.navigate(Screen.Login.route)

                            }
                        }
                        )

                    }
                    composable(Screen.MainScreen.route) {
                        MainScreen(navController = navController)
                    }
                    composable(Screen.Home.route) {
                        HomeScreen(navController = navController)
                    }
                    composable(Screen.Login.route)
                    {
                        LoginScreen(
                            navController = navController,
                            mGoogleSignInClient = mGoogleSignInClient
                        )
                    }
                    composable(Screen.SignUp.route){
                        SignUpScreen(navController = navController)
                    }
                    composable(Screen.Userdata.route){
                        UserDataScreen(navController = navController)
                    }
                    composable(Screen.Performance.route){
                        PerformanceScreen(navController = navController)
                    }
                    composable(Screen.Leaderboard.route){
                        LeaderboardScreen(navController=navController)
                    }
                    composable(Screen.ChangeClass.route){
                        ChangeClassScreen(navController=navController)
                    }
                    composable(Screen.TestSetsScreen.route){
                        TestSetsScreen(navController=navController)
                    }

//                    composable(
//                        route = Screen.ResultScreen.route,
//                        arguments = listOf(
//                            navArgument("score") { type = NavType.IntType },
//                            navArgument("totalQuestions") { type = NavType.IntType },
//                            navArgument("solutions") { type = NavType.StringType }
//                        )
//                    ) { backStackEntry ->
//                        val score = backStackEntry.arguments?.getInt("score") ?: 0
//                        val totalQuestions = backStackEntry.arguments?.getInt("totalQuestions") ?: 0
//                        val solutionsJson = backStackEntry.arguments?.getString("solutions") ?: "[]"
//                        val questionsJson = backStackEntry.arguments?.getString("questions") ?: "[]"
//
//                        ResultScreen(
//                            navController = navController,
//                            viewModel = viewModel()
////                            score = score,
////                            totalQuestions = totalQuestions,
////                            solutionsJson = solutionsJson,
////                            questionsJson = questionsJson
//                        )
//                    }
                }//NavHost end


            }


        }
    }
}