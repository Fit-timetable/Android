package ru.nsu.ftt.edit_lesson.data.remote_repository.model

import kotlinx.serialization.Serializable
import ru.nsu.fit.common.models.WeekDay

@Serializable
data class LessonDateRemote(
    var weekDay: WeekDay = WeekDay.MONDAY,
    var startTime: String = "00:00"
)

fun lessonDateRemote(block: LessonDateRemote.() -> Unit) = LessonDateRemote().apply(block)