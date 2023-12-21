package ru.nsu.fit.timetable.presentation.model

import ru.nsu.fit.common.models.LessonParity
import ru.nsu.fit.common.models.LessonType
import ru.nsu.fit.timetable.presentation.view.LessonTypeUi

data class LessonUi(
    var id: Int? = null,
    var startTime: String = "",
    var finishTime: String = "",
    var subject: String = "",
    var typeLesson: LessonTypeUi = LessonTypeUi.WindowSchedule,
    var room: String = "",
    var teacher: String = "",
    var parity: LessonParity = LessonParity.ALWAYS,
    var isClicked: Boolean = false,
    var indexInDay: Int = 1,
)

fun lessonUi(block: LessonUi.() -> Unit) = LessonUi().apply(block)

fun LessonType.mapToLessonTypeUi(): LessonTypeUi {
    return when (this) {
        LessonType.LECTURE -> LessonTypeUi.Lecture
        LessonType.SEMINAR -> LessonTypeUi.Seminar
        LessonType.PRACTICE -> LessonTypeUi.Seminar
        LessonType.WINDOW -> LessonTypeUi.WindowSchedule
    }
}