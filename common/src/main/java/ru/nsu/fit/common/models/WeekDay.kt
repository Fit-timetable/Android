package ru.nsu.fit.common.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class WeekDay {
    @SerialName("monday")
    MONDAY,

    @SerialName("tuesday")
    TUESDAY,

    @SerialName("wednesday")
    WEDNESDAY,

    @SerialName("thursday")
    THURSDAY,

    @SerialName("friday")
    FRIDAY,

    @SerialName("saturday")
    SATURDAY;

    fun mapToRus(): String {
        return when (this) {
            MONDAY -> "Пн"
            TUESDAY -> "Вт"
            WEDNESDAY -> "Ср"
            THURSDAY -> "Чт"
            FRIDAY -> "Пт"
            SATURDAY -> "Сб"
        }
    }
}

fun String.mapToWeekDay(): WeekDay {
    return when (this) {
        "Пн" -> WeekDay.MONDAY
        "Вт" -> WeekDay.TUESDAY
        "Ср" -> WeekDay.WEDNESDAY
        "Чт" -> WeekDay.THURSDAY
        "Пт" -> WeekDay.FRIDAY
        "Сб" -> WeekDay.SATURDAY
        else -> error("Not correctly date")
    }
}