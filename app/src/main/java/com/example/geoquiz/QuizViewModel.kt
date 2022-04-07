package com.example.geoquiz

import androidx.lifecycle.ViewModel


class QuizViewModel : ViewModel() {
    private val questionBank = listOf(
        Questions(R.string.question_1, true),
        Questions(R.string.question_2, false),
        Questions(R.string.question_3, false),
        Questions(R.string.question_4, true),
        Questions(R.string.question_5, true)
    )
    val questionBankSize = questionBank.size

    var currentIndex = 0

    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBankSize
    }
}