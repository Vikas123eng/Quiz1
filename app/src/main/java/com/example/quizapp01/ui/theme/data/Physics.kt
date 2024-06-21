
package com.example.quizapp01.ui.theme.data

import com.example.quizapp01.ui.theme.Question
import com.example.quizapp01.ui.theme.SubjectClassQuestions

data class Physics(
    val questions: List<Question>
) {
    companion object {
        fun getQuestions(subjectClassQuestions: SubjectClassQuestions): List<Question> {
            return when (subjectClassQuestions) {
                SubjectClassQuestions("Class 6", "Physics") -> listOf(
                    Question(
                        "What is the capital of France?",
                        listOf("Berlin", "Madrid", "Paris", "Rome"),
                        2
                    ),
                    Question(
                        "Which planet is known as the Red Planet?",
                        listOf("Earth", "Mars", "Venus", "Jupiter"),
                        1
                    ),
                    Question(
                        "What is the largest mammal in the world?",
                        listOf("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"),
                        1
                    ),
                    Question(
                        "Who wrote 'Romeo and Juliet'?",
                        listOf("Charles Dickens", "Jane Austen", "William Shakespeare", "Mark Twain"),
                        2
                    )
                )


//class 7
                SubjectClassQuestions("Class 7", "Physics") -> listOf(
                    Question(
                        "What is the capital of France?",
                        listOf("Berlin", "Madrid", "Paris", "Rome"),
                        2
                    ),
                    Question(
                        "Which planet is known as the Red Planet?",
                        listOf("Earth", "Mars", "Venus", "Jupiter"),
                        1
                    ),
                    Question(
                        "What is the largest mammal in the world?",
                        listOf("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"),
                        1
                    ),
                    Question(
                        "Who wrote 'Romeo and Juliet'?",
                        listOf("Charles Dickens", "Jane Austen", "William Shakespeare", "Mark Twain"),
                        2
                    )
                )

//class 8
                SubjectClassQuestions("Class 8", "Physics") -> listOf(
                    Question(
                        "What is the capital of France?",
                        listOf("Berlin", "Madrid", "Paris", "Rome"),
                        2
                    ),
                    Question(
                        "Which planet is known as the Red Planet?",
                        listOf("Earth", "Mars", "Venus", "Jupiter"),
                        1
                    ),
                    Question(
                        "What is the largest mammal in the world?",
                        listOf("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"),
                        1
                    ),
                    Question(
                        "Who wrote 'Romeo and Juliet'?",
                        listOf("Charles Dickens", "Jane Austen", "William Shakespeare", "Mark Twain"),
                        2
                    )
                )


                //class 9


                SubjectClassQuestions("Class 9", "Physics") -> listOf(
                    Question(
                        "What is the capital of France?",
                        listOf("Berlin", "Madrid", "Paris", "Rome"),
                        2
                    ),
                    Question(
                        "Which planet is known as the Red Planet?",
                        listOf("Earth", "Mars", "Venus", "Jupiter"),
                        1
                    ),
                    Question(
                        "What is the largest mammal in the world?",
                        listOf("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"),
                        1
                    ),
                    Question(
                        "Who wrote 'Romeo and Juliet'?",
                        listOf("Charles Dickens", "Jane Austen", "William Shakespeare", "Mark Twain"),
                        2
                    )
                )

//class 10

                SubjectClassQuestions("Class 10", "Physics") -> listOf(
                    Question(
                        "What is the capital of France?",
                        listOf("Berlin", "Madrid", "Paris", "Rome"),
                        2
                    ),
                    Question(
                        "Which planet is known as the Red Planet?",
                        listOf("Earth", "Mars", "Venus", "Jupiter"),
                        1
                    ),
                    Question(
                        "What is the largest mammal in the world?",
                        listOf("Elephant", "Blue Whale", "Giraffe", "Hippopotamus"),
                        1
                    ),
                    Question(
                        "Who wrote 'Romeo and Juliet'?",
                        listOf("Charles Dickens", "Jane Austen", "William Shakespeare", "Mark Twain"),
                        2
                    )
                )

                // Add more cases for other classes and subjects as needed
                else -> emptyList()
            }
        }
    }
}
