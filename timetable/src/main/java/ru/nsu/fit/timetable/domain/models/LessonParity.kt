package ru.nsu.fit.timetable.domain.models

import kotlinx.serialization.SerialName

enum class LessonParity {
    @SerialName("EVEN")
    EVEN,

    @SerialName("ODD")
    ODD,

    @SerialName("ALWAYS")
    ALWAYS
}