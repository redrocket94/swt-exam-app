package com.redrocket94.swt_exam_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

a

class MainActivity : AppCompatActivity() {

    // Handle everything that needs to be set when screen is created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Connects this code to the UI of the screen (XML file) so that we can find required buttons etc.

        title = "Quirky Quiz App"

        // Sets behavior for when "Done" button is clicked
        nameDoneButton.setOnClickListener {

            // Takes the text (the name that was input) from the name input field and checks if name is viable
            val nameInput = nameEditText.text.toString()
            when (isNameViable(nameInput)) {
                // If name is viable, go to quiz page with the name, otherwise send a Toast to the user to let them know the name was invalid
                true -> goToQuiz(nameInput)
                false -> Toast.makeText(this, "Invalid name! Try again.", Toast.LENGTH_SHORT).show()
            }

        }
    }

    // Adds the user name to an intent for use in the next page with the "NAME_VAL" key
    private fun goToQuiz(nameInput: String) {
        val intent = Intent(this, QuizActivity::class.java).apply {
            putExtra("NAME_VAL", nameInput)
        }

        // Sends user to quiz page, "starts" the quiz screen
        startActivity(intent)
    }

    // Returns false if name is blank or traverses the string to make sure every character is a letter or a dash (as these are only characters allowed)
    private fun isNameViable(text: String): Boolean {
        if( text.isBlank()) return false
        text.forEach {
            if (!it.isLetter() && it != '-') return false
        }
        return true
    }



}
