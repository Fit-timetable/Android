package ru.nsu.fit.timetable.domain.models

class LessonForm(
    val evenWeeksOnly: Boolean,
    val oddWeeksOnly: Boolean,
    val place: Place,
    val startTime: LessonDate,
    val subject: String,
    val teacher: String,
    val type: LessonType
)