package ru.nsu.fit.timetable.domain.models

import kotlinx.serialization.SerialName

enum class LessonType {
    @SerialName("LECTURE")
    LECTURE,

    @SerialName("SEMINAR")
    SEMINAR,

    @SerialName("PRACTICE")
    PRACTICE
}