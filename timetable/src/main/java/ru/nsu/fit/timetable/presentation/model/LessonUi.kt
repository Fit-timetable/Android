package ru.nsu.fit.timetable.presentation.model

import ru.nsu.fit.timetable.domain.models.LessonParity
import ru.nsu.fit.timetable.presentation.view.LessonTypeUi

data class LessonUi(
    var id: Int? = null,
    var time: String = "",
    var subject: String = "",
    var typeLesson: LessonTypeUi = LessonTypeUi.WindowSchedule,
    var room: String = "",
    var teacher: String = "",
    var parity: LessonParity = LessonParity.ALWAYS
)

fun lessonUi(block: LessonUi.() -> Unit) = LessonUi().apply(block)