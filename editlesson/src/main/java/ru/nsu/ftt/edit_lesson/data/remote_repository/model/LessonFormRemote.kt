package ru.nsu.ftt.edit_lesson.data.remote_repository.model

import kotlinx.serialization.Serializable
import ru.nsu.fit.common.models.LessonParity
import ru.nsu.fit.common.models.LessonType

@Serializable
class LessonFormRemote(
    var subjectId: Int = 0,
    var type: LessonType = LessonType.SEMINAR,
    var date: LessonDateRemote = lessonDateRemote {},
    var place: PlaceRemote = placeRemote { },
    var parity: LessonParity = LessonParity.ALWAYS
)

fun lessonFormRemote(block: LessonFormRemote.() -> Unit) = LessonFormRemote().apply(block)