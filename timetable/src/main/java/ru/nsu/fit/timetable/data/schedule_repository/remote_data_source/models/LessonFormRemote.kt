package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models

import ru.nsu.fit.timetable.domain.models.LessonType

class LessonFormRemote(
    val evenWeeksOnly: Boolean = false,
    val oddWeeksOnly: Boolean = false,
    val place: PlaceRemote = placeRemote { },
    val startTime: LessonDateRemote = lessonDateRemote {},
    val subject: String = "",
    val teacher: String = "",
    val type: LessonType = LessonType.SEMINAR
)

fun lessonFormRemote(block: LessonFormRemote.() -> Unit) = LessonFormRemote().apply(block)