package com.example.quizapp01.ui.theme.data

import com.google.firebase.firestore.FirebaseFirestore

data class Quiz(
    var id: String = "",
    var classes: String = "",
    var title: String = ""
)



//fun fetchClasses() {
//    val db = FirebaseFirestore.getInstance()
//    val collectionReference = db.collection("classes")
//
//    collectionReference.get()
//        .addOnSuccessListener { documents ->
//            for (document in documents) {
//                val quiz = document.toObject(Quiz::class.java)
//                quiz.id = document.id // setting the document ID to the quiz object
//                // Log or use the fetched quiz object as needed
//                println("Quiz: $quiz")
//            }
//        }
//        .addOnFailureListener { exception ->
//            // Handle any errors
//            println("Error getting documents: $exception")
//        }
//}
