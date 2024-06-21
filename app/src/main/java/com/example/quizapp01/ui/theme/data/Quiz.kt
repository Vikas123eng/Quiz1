package com.example.quizapp01.ui.theme.data

data class Quiz(
     var id: String="",
     var classes: String="",
    var title: String="",
    var questions: MutableMap<String,Questions> = mutableMapOf()

)
