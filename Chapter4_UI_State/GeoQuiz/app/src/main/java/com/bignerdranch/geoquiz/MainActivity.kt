package com.bignerdranch.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.prev_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener { view: View ->
//            Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
            checkAnswer(true)
            isAnswered()
        }

        falseButton.setOnClickListener { view: View ->
//          val myToast = Toast.makeText(this, R.string.false_toast, Toast.LENGTH_SHORT)
//            myToast.setGravity(Gravity.TOP, 200,200)
//            myToast.show()
            checkAnswer(false)
            isAnswered()
        }

        questionTextView.setOnClickListener{
//            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            refreshButtons() // re-enable buttons after next button press
            updateQuestion() // updates when next button is pressed

        }

        prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion() // updates when next button is pressed
        }

        // disable buttons after answer
        updateQuestion() // for the true false button update

    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }

    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionAText
        questionTextView.setText(questionTextResId)
    }

    private fun checkAnswer(userAnswer: Boolean)
    {
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResId = if (userAnswer == correctAnswer) {
                R.string.correct_toast
            } else {
                R.string.false_toast
            }
        Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
        }

    private fun isAnswered(){
        falseButton.isEnabled = false
        trueButton.isEnabled = false
    }

    private fun refreshButtons(){
        falseButton.isEnabled = true
        trueButton.isEnabled = true
    }
    }



