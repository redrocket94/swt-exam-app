package com.redrocket94.swt_exam_app

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.redrocket94.swt_exam_app.quizdata.quizzes
import kotlinx.android.synthetic.main.activity_quiz.*


class QuizActivity : AppCompatActivity() {

    // Set variable to know how far we are in the quiz
    var quizPoint = 0

    // Declare or list of buttons out of onCreate scope so that it can be accessed in other scopes
    lateinit var answerButtonList: List<Button>

    // Handle everything that needs to be set when screen is created, initialization of buttons and data
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz) // Connects this code to the UI of the screen (XML file) so that we can find required buttons etc.

        title = "Quirky Quiz App"

        // Send a Toast to the user, greeting them using their name by retrieving it using the "NAME_VAL" key as it was saved with in the MainActivity.kt file
        Toast.makeText(this, "Hello, ${intent.getStringExtra("NAME_VAL")}!", Toast.LENGTH_SHORT).show()

        initQuiz() // Initializes our buttons and sets their behavior
        getNextQuiz(quizzes[quizPoint] as Map<String, List<String>>) // Retrieves data used for the first quiz
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initQuiz() {

        // Initialize list of buttons
        answerButtonList = listOf<Button>(
            answerButton01,
            answerButton02,
            answerButton03,
            answerButton04
        )

        // Sets behavior when clicked for every button in our list we declared above
        answerButtonList.forEach {
            it.setOnClickListener { showAnswers(it as Button) }
        }

        // Sets text to autosize based on screen size with a minimum size of 12dp, maximum of 36 and with a step of 2 between the two
        answerButtonList.forEach {
            it.setAutoSizeTextTypeUniformWithConfiguration(12, 36, 2, TypedValue.COMPLEX_UNIT_DIP)
        }
    }

    // Handles changing button color when an answer is clicked
    private fun showAnswers(v: Button) {
        // When clicked, button list is traversed and the button with the text that matches the correct answer is set to green and the others are set to red
        answerButtonList.forEach {
            if (it.text == quizzes[quizPoint]["quizCorrectAnswer"]) {
                it.setBackgroundColor(Color.parseColor("#00FF00"))
            } else {
                it.setBackgroundColor(Color.parseColor("#FF0000"))
            }
        }

        // Sends a toast to the user with "Correct" or "Incorrect" depending whether the answer was right or wrong
        when (v.text == quizzes[quizPoint]["quizCorrectAnswer"]) {
            true ->  Toast.makeText(this, "Correct!\nTap anywhere to continue!", Toast.LENGTH_SHORT).show()
            false ->  Toast.makeText(this, "Incorrect!\nTap anywhere to continue!", Toast.LENGTH_SHORT).show()
        }

        // Sets up dummy view for user to click as this current quiz is over and user wants to proceed
        setupClickToContinueView()
    }

    // Handles sizing a dummy view for user to click to continue
    private fun setupClickToContinueView() {
        // Sets the size of dummy view (initial size is 0x0) to match that of parent (whole screen)
        setViewSize(MATCH_PARENT, MATCH_PARENT)

        // Sets behavior when clicked, getting new data for quiz and setting size back to 0x0
        clickToContinueView.setOnClickListener {
            if (quizPoint <= quizzes.size) {
                quizPoint++
                getNextQuiz(quizzes[quizPoint] as Map<String, List<String>>)

                setViewSize(0, 0)
            }
        }
    }

    // Takes parameters height and width and sets our dummy view to the values given
    private fun setViewSize(height: Int, width: Int) {
        val params = clickToContinueView.layoutParams
        params.height = height
        params.width = width
        clickToContinueView.layoutParams = params
    }


    // Gets data from our quizData holder and sets the text in the buttons and question text
    private fun getNextQuiz(quizData: Map<String, List<String>>) {
        quizText.text = quizData["quizQuestion"].toString()
        answerButton01.text = quizData["quizPossibleAnswers"]?.get(0).toString()
        answerButton02.text = quizData["quizPossibleAnswers"]?.get(1).toString()
        answerButton03.text = quizData["quizPossibleAnswers"]?.get(2).toString()
        answerButton04.text = quizData["quizPossibleAnswers"]?.get(3).toString()

        // Clear the color of buttons as this is a new quiz
        clearButtonColors()
    }

    // Iterate through every color in list and set their background resource (color) to that of the default button
    private fun clearButtonColors() {
        answerButtonList.forEach {
            it.setBackgroundResource(android.R.drawable.btn_default);
        }
    }
}
