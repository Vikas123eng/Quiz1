package com.example.quizapp01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quizapp01.ui.theme.HomeScreen
import com.example.quizapp01.ui.theme.QuizApp01Theme
import com.example.quizapp01.ui.theme.SignUpScreen
import com.example.quizapp01.ui.theme.SplashScreen
import com.example.quizapp01.ui.theme.data.Quiz
import com.example.quizapp01.ui.theme.ui.login.Screen
import com.example.quizapp01.ui.theme.ui.login.UserDataScreen
import com.google.android.gms.auth.api.signin.GoogleSignIn.getClient
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder
import com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.example.quizapp01.ui.theme.LoginScreen as LoginScreen


class MainActivity : ComponentActivity() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
//    private fun setUpFirestore(){
//
//         firestore=FirebaseFirestore.getInstance()
//        val collectionReference:CollectionReference =firestore.collection("quiz02")
//        collectionReference.addSnapshotListener { value, error ->
//            if (value==null||error!=null){
//
//                Toast.makeText(this,"Having Error Help",Toast.LENGTH_SHORT).show()
//                return@addSnapshotListener
//            }
//            Log.d("DATA",value.toObjects(Quiz::class.java).toString())
//
//        }
//    }


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
                                navController.navigate(Screen.Home.route)
                            } else {
                                navController.navigate(Screen.Login.route)

                            }
                        }
                        )

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

                }//NavHost end


            }


        }
    }
}