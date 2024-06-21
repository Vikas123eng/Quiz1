package com.example.quizapp01.ui.theme.data

data class QuizSets01(
    val subject: String = "",
    val className: String = "",
    val questions:  MutableMap<String,Questions> = mutableMapOf()
)
