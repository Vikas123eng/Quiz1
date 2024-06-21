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

private lateinit var firestore: FirebaseFirestore
    private fun setUpFirestore(){

         firestore=FirebaseFirestore.getInstance()
        val collectionReference:CollectionReference =firestore.collection("quiz02")
        collectionReference.addSnapshotListener { value, error ->
            if (value==null||error!=null){

                Toast.makeText(this,"Having Error Help",Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            Log.d("DATA",value.toObjects(Quiz::class.java).toString())

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var auth: FirebaseAuth? = null
        auth = Firebase.auth







        val currentUser = auth.currentUser
        setContent {
            QuizApp01Theme {


//// Create a new user with a first and last name
//                var user = hashMapOf(
//                    "first" to "Ada",
//                    "last" to "Lovelace",
//                    "born" to 1815
//                )
//
//// Add a new document with a generated ID
//                db.collection("users")
//                    .add(user)
//                    .addOnSuccessListener { documentReference ->
//                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w(TAG, "Error adding document", e)
//                    }
//                // Create a new user with a first, middle, and last name
//                 user = hashMapOf(
//                    "first" to "Alan",
//                    "middle" to "Mathison",
//                    "last" to "Turing",
//                    "born" to 1912
//                )
//
//// Add a new document with a generated ID
//                db.collection("users")
//                    .add(user)
//                    .addOnSuccessListener { documentReference ->
//                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w(TAG, "Error adding document", e)
//                    }
//



//                val quizQuestions = listOf(
//                    QuizQuestions("Question 1", "Option A", "Option B", "Option A", "Option D"),
//                    QuizQuestions("Question 2", "Option C", "Option D", "Option C", "Option D"),
//                    // ... add more questions as needed
//                )
//                db.collection("Quiz")
//                    .add(mapOf("questions" to quizQuestions))
//                    .addOnSuccessListener { documentReference ->
//                        Log.d(TAG, "Quiz added with ID: ${documentReference.id}")
//                    }
//                    .addOnFailureListener { e ->
//                        Log.w(TAG, "Error adding quiz", e)
//                    }



                    SplashScreen(onTimeOut = {

                                    setUpFirestore()
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


//private fun logQuizCollection() {
//    val db = Firebase.firestore
//    val quizCollectionRef: CollectionReference = db.collection("QuizQuestions")
//    quizCollectionRef.get()
//        .addOnSuccessListener { querySnapshot ->
//            for (document in querySnapshot) {
//                Log.d(TAG, "${document.id} => ${document.data}")
//            }
//        }
//        .addOnFailureListener { e ->
//            Log.w(TAG, "Error getting documents from Quiz collection", e)
//        }
//}
//

