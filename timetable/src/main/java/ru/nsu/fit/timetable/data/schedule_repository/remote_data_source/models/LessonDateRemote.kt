package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models

import kotlinx.serialization.Serializable
import ru.nsu.fit.timetable.domain.models.WeekDay

@Serializable
data class LessonDateRemote(
    val weekDay: WeekDay = WeekDay.MONDAY,
    val startTime: String = "00:00"
)

fun lessonDateRemote(block: LessonDateRemote.()->Unit) = LessonDateRemote().apply(block)