package com.example.quizapp01

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.quizapp01.ui.theme.Home
import com.example.quizapp01.ui.theme.LoginActivity
import com.example.quizapp01.ui.theme.QuizApp01Theme
import com.example.quizapp01.ui.theme.SplashScreen
import com.example.quizapp01.ui.theme.data.Quiz
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase


class MainActivity : ComponentActivity() {

//private lateinit var firestore: FirebaseFirestore
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
        var auth: FirebaseAuth? = null
        auth = Firebase.auth
        val currentUser = auth.currentUser

        setContent {
            QuizApp01Theme {

                    SplashScreen(onTimeOut = {
                                   // setUpFirestore()
                        if (currentUser != null) {
                            startActivity(Intent(this@MainActivity, Home::class.java))
                           finish()
                        } else {
                            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            finish()

                        }
                    }


                    )



                }
            }



        }
    }
