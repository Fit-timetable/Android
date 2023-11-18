package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models

import kotlinx.serialization.Serializable
import ru.nsu.fit.timetable.domain.models.LessonParity
import ru.nsu.fit.timetable.domain.models.LessonType

@Serializable
data class LessonRemote(
    var id: Int? = 0,
    var place: PlaceRemote = placeRemote { },
    var startTime: String = "",
    var subject: String = "",
    var teacher: String = "",
    var type: LessonType = LessonType.SEMINAR,
    var parity: LessonParity = LessonParity.ALWAYS,
)

fun lessonRemote(block: LessonRemote.() -> Unit) = LessonRemote().apply(block)


