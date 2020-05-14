package com.redrocket94.swt_exam_app.quizdata

// Structure to hold our quizData, there is probably a much simpler way to do this, but its a list of maps that each contains
// the quiz question (as a string), the possible answers (as a list of strings) and the correct answer (as a string)
val quizzes = listOf(
    mapOf(
        "quizQuestion" to "Which letter does Benazeer start with?",
        "quizPossibleAnswers" to listOf(
            "P",
            "B",
            "N",
            "L"
        ),
        "quizCorrectAnswer" to "B"
    ),
    mapOf(
        "quizQuestion" to "Which course does Arturo teach?",
        "quizPossibleAnswers" to listOf(
            "The Simpsons and Philosophy",
            "Software Testing",
            "Introduction to Beekeeping",
            "Underwater Basket Weaving"
        ),
        "quizCorrectAnswer" to "Software Testing"
    ),
    mapOf(
        "quizQuestion" to "How many hands does the average bird have?",
        "quizPossibleAnswers" to listOf(
            "12",
            "0",
            "1",
            "4.5"
        ),
        "quizCorrectAnswer" to "0"
    )
)