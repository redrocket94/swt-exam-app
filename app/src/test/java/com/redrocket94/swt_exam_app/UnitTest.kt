package com.redrocket94.swt_exam_app

import android.os.Build
import com.redrocket94.swt_exam_app.quizdata.quizzes
import kotlinx.android.synthetic.main.activity_quiz.*
import org.junit.Test

import org.junit.Assert.*
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class UnitTest {

    @Test
    fun quiz_data_returnNotNull() {
        val quizActivity: QuizActivity = Robolectric.setupActivity(QuizActivity::class.java)

        assertNotNull(quizActivity.answerButton01.text.toString())
        assertNotNull(quizActivity.quizText.text.toString())
    }

    @Test
    fun advance_quizPoint_returnTrue() {
        val quizActivity: QuizActivity = Robolectric.setupActivity(QuizActivity::class.java)

        val initialQuizPoint = quizActivity.quizPoint

        quizActivity.answerButton01.performClick()
        quizActivity.clickToContinueView.performClick()

        assertTrue(quizActivity.quizPoint == (initialQuizPoint + 1))
    }
}
