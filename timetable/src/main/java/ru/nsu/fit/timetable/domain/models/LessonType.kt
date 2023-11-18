package ru.nsu.fit.timetable.domain.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.nsu.fit.timetable.presentation.view.LessonTypeUi

@Serializable
enum class LessonType {
    @SerialName("LECTURE")
    LECTURE,

    @SerialName("SEMINAR")
    SEMINAR,

    @SerialName("LABORATORY")
    LABORATORY,

    @SerialName("PRACTICE")
    PRACTICE;

    fun mapToLessonTypeUi(): LessonTypeUi {
        return when (this) {
            LECTURE -> LessonTypeUi.Lecture
            SEMINAR -> LessonTypeUi.Seminar
            PRACTICE -> LessonTypeUi.Seminar
            LABORATORY -> LessonTypeUi.Seminar
        }
    }
}