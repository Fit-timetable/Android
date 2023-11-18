package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models

import kotlinx.serialization.Serializable

@Serializable
data class DayScheduleRemote(
    var lessons: List<LessonRemote> = emptyList(),
)

fun dayScheduleRemote(block: DayScheduleRemote.() -> Unit) = DayScheduleRemote().apply(block)