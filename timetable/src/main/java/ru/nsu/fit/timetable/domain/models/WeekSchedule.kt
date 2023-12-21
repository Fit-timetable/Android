package ru.nsu.fit.timetable.domain.models

import ru.nsu.fit.common.models.WeekDay

data class WeekSchedule(
    var monday: List<Lesson> = emptyList(),
    var tuesday: List<Lesson> = emptyList(),
    var wednesday: List<Lesson> = emptyList(),
    var thursday: List<Lesson> = emptyList(),
    var friday: List<Lesson> = emptyList(),
    var saturday: List<Lesson> = emptyList(),
) {
    fun getDaySchedule(weekDay: WeekDay): List<Lesson> {
        return when (weekDay) {
            WeekDay.MONDAY -> monday
            WeekDay.TUESDAY -> tuesday
            WeekDay.WEDNESDAY -> wednesday
            WeekDay.THURSDAY -> thursday
            WeekDay.FRIDAY -> friday
            WeekDay.SATURDAY -> saturday
        }
    }
}

fun weekSchedule(block: WeekSchedule.() -> Unit) = WeekSchedule().apply(block)