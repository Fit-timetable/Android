package ru.nsu.fit.timetable.presentation.model

import java.util.Calendar

data class TopBarUi(
    val group : String?,
    val date : String = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString(),
    val month: String = Month.values()[Calendar.getInstance().get(Calendar.MONTH)].nameRusMonth,
    val parityWeek : String = ParityWeek.values()[(Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) % 2)].nameWeek,
    val fixedSchedule: Boolean = true
)

enum class Month(val nameRusMonth : String) {
    January("Января"),
    February("Февраля"),
    March("Марта"),
    April("Апреля"),
    May("Мая"),
    June("Июня"),
    July("Июля"),
    August("Август"),
    September("Сентября"),
    October("Октября"),
    November("Ноября"),
    December("Декабря")
}

enum class ParityWeek(val nameWeek: String) {
    EVEN("Четная"),
    ODD("Нечетная")
}
