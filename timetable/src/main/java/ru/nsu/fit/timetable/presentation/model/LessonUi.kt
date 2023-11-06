package ru.nsu.fit.timetable.presentation.model

import ru.nsu.fit.timetable.presentation.view.LessonType

data class LessonUi(
    val time: String,
    val subject: String = "",
    val typeClass : LessonType = LessonType.WindowSchedule,
    val auditorium: String = "",
    val teacher: String = "",
    val evenWeeksOnly: Boolean = true,
    val oddWeeksOnly: Boolean = true,

    )