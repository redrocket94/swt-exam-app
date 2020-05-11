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

    var quizPoint = 0

    lateinit var answerButtonList: List<Button>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        title = "Quirky Quiz App"

        Toast.makeText(this, "Hello, ${intent.getStringExtra("NAME_VAL")}!", Toast.LENGTH_SHORT).show()

        initQuiz()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initQuiz() {
        answerButtonList = listOf<Button>(
            answerButton01,
            answerButton02,
            answerButton03,
            answerButton04
        )

        getNextQuiz(quizzes[quizPoint] as Map<String, List<String>>)

        answerButtonList.forEach {
            it.setOnClickListener { showAnswers(it as Button) }
        }

        answerButtonList.forEach {
            it.setAutoSizeTextTypeUniformWithConfiguration(12, 36, 2, TypedValue.COMPLEX_UNIT_DIP)
        }
    }

    private fun showAnswers(v: Button) {
        answerButtonList.forEach {
            if (it.text == quizzes[quizPoint]["quizCorrectAnswer"]) {
                it.setBackgroundColor(Color.parseColor("#00FF00"))
            } else {
                it.setBackgroundColor(Color.parseColor("#FF0000"))
            }
        }

        when (v.text == quizzes[quizPoint]["quizCorrectAnswer"]) {
            true ->  Toast.makeText(this, "Correct!\nTap anywhere to continue!", Toast.LENGTH_SHORT).show()
            false ->  Toast.makeText(this, "Incorrect!\nTap anywhere to continue!", Toast.LENGTH_SHORT).show()
        }

        setupClickToContinueView()
    }

    private fun setupClickToContinueView() {
        setViewSize(MATCH_PARENT, MATCH_PARENT)

        clickToContinueView.setOnClickListener {
            if (quizPoint <= quizzes.size) {
                quizPoint++
                getNextQuiz(quizzes[quizPoint] as Map<String, List<String>>)

                setViewSize(0, 0)
            }
        }
    }

    private fun setViewSize(height: Int, width: Int) {
        val params = clickToContinueView.layoutParams
        params.height = height
        params.width = width
        clickToContinueView.layoutParams = params
    }


    private fun getNextQuiz(quizData: Map<String, List<String>>) {
        quizText.text = quizData["quizQuestion"].toString()
        answerButton01.text = quizData["quizPossibleAnswers"]?.get(0).toString()
        answerButton02.text = quizData["quizPossibleAnswers"]?.get(1).toString()
        answerButton03.text = quizData["quizPossibleAnswers"]?.get(2).toString()
        answerButton04.text = quizData["quizPossibleAnswers"]?.get(3).toString()

        clearButtonColors()
    }

    private fun clearButtonColors() {
        answerButtonList.forEach {
            it.setBackgroundResource(android.R.drawable.btn_default);
        }
    }
}
