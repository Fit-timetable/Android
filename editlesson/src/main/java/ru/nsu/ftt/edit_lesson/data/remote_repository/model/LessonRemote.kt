package ru.nsu.ftt.edit_lesson.data.remote_repository.model

import kotlinx.serialization.Serializable
import ru.nsu.fit.common.models.LessonParity
import ru.nsu.fit.common.models.LessonType

@Serializable
data class LessonRemote(
    var id: Int? = 0,
    var place: PlaceRemote? = placeRemote { },
    var subject: String? = "",
    var teacher: String? = "",
    var startTime: String = "",
    var finishTime: String = "",
    var type: LessonType = LessonType.SEMINAR,
    var parity: LessonParity? = LessonParity.ALWAYS,
)

fun lessonRemote(block: LessonRemote.() -> Unit) = LessonRemote().apply(block)


