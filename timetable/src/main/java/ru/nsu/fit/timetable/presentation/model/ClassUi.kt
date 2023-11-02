package ru.nsu.fit.timetable.presentation.model

import ru.nsu.fit.timetable.presentation.view.ClassType

data class ClassUi(
    val timeStart: String,
    val timeEnd: String,
    val subject: String,
    val typeClass : ClassType,
    val auditorium: String
)