package ru.nsu.fit.timetable.domain.models

class LessonForm(
    val evenWeeksOnly: Boolean = false,
    val oddWeeksOnly: Boolean = false,
    val place: Place = place { },
    val startTime: LessonDate = lessonDate { },
    val subject: String = "",
    val teacher: String = "",
    val type: LessonType = LessonType.LECTURE
)

fun lessonForm(block: LessonForm.() -> Unit) = LessonForm().apply(block)