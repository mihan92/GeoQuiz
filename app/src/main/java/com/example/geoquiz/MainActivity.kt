package com.example.geoquiz


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {

    private lateinit var buttonTrue: Button
    private lateinit var buttonFalse: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView

    private val provider: QuizViewModel by lazy {
        ViewModelProvider(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonTrue = findViewById(R.id.button_true)
        buttonFalse = findViewById(R.id.button_false)
        nextButton = findViewById(R.id.button_next)
        questionTextView = findViewById(R.id.text_view_question)

        updateQuestion()

        nextButton.setOnClickListener {
            provider.moveToNext()
            updateQuestion()
            buttonsIsVisible()
            nextButton.isEnabled = provider.currentIndex != provider.questionBankSize - 1
        }


        buttonTrue.setOnClickListener {
            chekAnswer(true)
            buttonsIsInvisible()
        }

        buttonFalse.setOnClickListener {
            chekAnswer(false)
            buttonsIsInvisible()
        }
    }

    private fun updateQuestion() {
        val questionTextId = provider.currentQuestionText
        questionTextView.setText(questionTextId)
    }

    private fun chekAnswer(userAnswer: Boolean) {
        val correctAnswer = provider.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            R.string.toast_true
        } else
            R.string.toast_false
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

    private fun buttonsIsVisible(){
        buttonTrue.isEnabled = true
        buttonFalse.isEnabled = true
    }

    private fun buttonsIsInvisible(){
        buttonTrue.isEnabled = false
        buttonFalse.isEnabled = false
    }
}
