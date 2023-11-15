package ru.nsu.fit.timetable.presentation

import ru.nsu.fit.timetable.presentation.model.TopBarUi
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.model.LessonUi
import java.util.Calendar

data class TimeTableState(
    val loading: Boolean = false,
    val error: String? = null,
    val lessonsUi: List<LessonUi>? = null,
    val group: TopBarUi = TopBarUi(
        group = "20201",
        date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH).toString(),
        month = Calendar.getInstance().get(Calendar.MONTH).toString(),
        parityWeek = (Calendar.getInstance().get(Calendar.WEEK_OF_MONTH) % 2).toString(),
        fixedSchedule = true
    ),
    val dates: List<DateUi>
)


sealed class TimeTableEvent {
    class OnGetScheduleForDayClick(
        val group: String,
        val date: DateUi
    ) : TimeTableEvent()

}

sealed class TimeTableCommand {


}