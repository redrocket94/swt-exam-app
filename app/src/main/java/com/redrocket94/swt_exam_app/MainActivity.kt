package com.redrocket94.swt_exam_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Quirky Quiz App"

        nameDoneButton.setOnClickListener {
            val nameInput = nameEditText.text.toString()
            when (isNameViable(nameInput)) {
                true -> goToQuiz(nameInput)
                false -> Toast.makeText(this, "Invalid name! Try again.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun goToQuiz(nameInput: String) {
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra("NAME_VAL", nameInput)
        }
        startActivity(intent)
    }

    private fun isNameViable(text: String): Boolean {
        if( text.isBlank()) return false
        text.forEach {
            if (!it.isLetter() && it != '-') return false
        }
        return true
    }



}
