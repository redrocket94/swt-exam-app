package com.redrocket94.swt_exam_app

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.CoreMatchers.containsString
import org.junit.Test

class IntegrationTest {

    @Test
    fun insertNewQuiz() {
        // Launch activity
        val scenario = launchActivity<QuizActivity>()

        // Recreate activity to see if first data remains
        scenario.recreate()
        onView(withId(R.id.quizText)).check(matches(withText(containsString("Which letter does Benazeer start with?"))))
    }
}