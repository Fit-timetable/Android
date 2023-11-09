package ru.nsu.fit.timetable.domain.models

import java.sql.Time

data class LessonDate(
    val weekDay: WeekDay,
    val startTime: Time
)