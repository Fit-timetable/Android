package ru.nsu.fit.timetable.domain.models

import java.sql.Time

data class LessonDate(
    var weekDay: WeekDay = WeekDay.MONDAY,
    var startTime: Time = Time.valueOf("00:00")
)

fun lessonDate(block: LessonDate.()->Unit) = LessonDate().apply(block)