package ru.nsu.fit.timetable.domain.models

import java.util.Date

data class Lesson(
    val evenWeeksOnly: Boolean,
    val oddWeeksOnly: Boolean,
    val place: Place,
    val startTime: Date,
    val subject: String,
    val teacher: String,
    val type: LessonType
)