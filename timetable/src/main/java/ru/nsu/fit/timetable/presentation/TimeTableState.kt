package ru.nsu.fit.timetable.presentation

import ru.nsu.fit.timetable.presentation.model.TopBarUi
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.model.LessonUi

data class TimeTableState(
    val loading: Boolean = false,
    val error: String = "",
    val lessonsUi: List<LessonUi> = emptyList(),
    val group: TopBarUi = TopBarUi(
        group = "20201"
    ),
    val dates: List<DateUi>
)


sealed class TimeTableEvent {
    class OnGetScheduleForDayClick(
        val group: String,
        val date: DateUi
    ) : TimeTableEvent()

    class OnClickForwardWeek(val group : String, val offsetWeek : Int) : TimeTableEvent()
}

sealed class TimeTableCommand {}


