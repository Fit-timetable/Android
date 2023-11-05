package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models

import kotlinx.serialization.Serializable
import ru.nsu.fit.timetable.domain.models.LessonType

@Serializable
data class LessonRemote(
    val evenWeeksOnly: Boolean = false,
    val oddWeeksOnly: Boolean = false,
    val place: PlaceRemote = placeRemote { },
    val startTime: String = "",
    val subject: String = "",
    val teacher: String = "",
    val type: LessonType = LessonType.SEMINAR
)

fun lessonRemote(block: LessonRemote.() -> Unit) = LessonRemote().apply(block)


