package ru.nsu.fit.timetable.domain.models

data class WeekSchedule(
    val monday: List<Lesson>,
    val tuesday: List<Lesson>,
    val wednesday: List<Lesson>,
    val thursday: List<Lesson>,
    val friday: List<Lesson>,
    val saturday: List<Lesson>,
)