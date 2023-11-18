package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class WeekScheduleRemote(
    @SerialName("monday")
    val monday: DayScheduleRemote = dayScheduleRemote {},

    @SerialName("tuesday")
    val tuesday: DayScheduleRemote = dayScheduleRemote {},

    @SerialName("wednesday")
    val wednesday: DayScheduleRemote = dayScheduleRemote {},

    @SerialName("thursday")
    val thursday: DayScheduleRemote = dayScheduleRemote {},

    @SerialName("friday")
    val friday: DayScheduleRemote = dayScheduleRemote {},

    @SerialName("saturday")
    val saturday: DayScheduleRemote = dayScheduleRemote {},
)

fun weekScheduleRemote(block: WeekScheduleRemote.() -> Unit) = WeekScheduleRemote().apply(block)