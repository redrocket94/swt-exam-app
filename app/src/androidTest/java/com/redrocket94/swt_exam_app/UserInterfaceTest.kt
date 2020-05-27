package com.redrocket94.swt_exam_app

import android.app.Activity
import android.content.Intent
import androidx.test.espresso.Espresso
import org.junit.Test

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.matcher.IntentMatchers.toPackage
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule

import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.not
import org.junit.Rule


import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserInterfaceTest {

    @Test
    fun emptyNameInputInvalid() {
        // Gets our activity so we can run checks on it
        var nameActivity: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
        nameActivity.launchActivity(Intent())

        // Performs a click on our DONE button without inputting a name
        onView(withId(R.id.nameDoneButton)).perform(click())

        // Checks whether the toast with the "invalid" text shows up after trying to click done without inputting a name
        onView(withText("Invalid name! Try again.")).inRoot(withDecorView(not(nameActivity.activity.window.decorView))).check(
            matches(isDisplayed()))
    }

    @Test
    fun nameInputValid() {

        val validName = "examplename"
        var nameActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

        nameActivity.launchActivity(Intent())

        // Inputs a valid name in the input box
        onView(withId(R.id.nameEditText)).perform(typeText(validName))

        // Close soft keyboard so we can click the DONE button
        Espresso.closeSoftKeyboard()

        // Performs a click on our DONE button without inputting a name
        onView(withId(R.id.nameDoneButton)).perform(click())

        // Check that we have been sent to the correct screen
        intended(hasComponent(QuizActivity::class.java.name))
    }
}
