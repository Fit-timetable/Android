package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeekSchedule(
    @SerialName("monday")
    val monday: List<LessonRemote> = emptyList(),

    @SerialName("tuesday")
    val tuesday: List<LessonRemote> = emptyList(),

    @SerialName("wednesday")
    val wednesday: List<LessonRemote> = emptyList(),

    @SerialName("thursday")
    val thursday: List<LessonRemote> = emptyList(),

    @SerialName("friday")
    val friday: List<LessonRemote> = emptyList(),

    @SerialName("saturday")
    val saturday: List<LessonRemote> = emptyList(),
)

fun weekSchedule(block: WeekSchedule.() -> Unit) = WeekSchedule().apply(block)