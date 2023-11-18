package ru.nsu.fit.timetable.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.nsu.fit.timetable.domain.ScheduleInteractorImpl
import ru.nsu.fit.timetable.domain.models.mapToWeekDay
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.model.LessonUi
import ru.nsu.fit.timetable.presentation.model.TopBarUi
import ru.nsu.fit.timetable.presentation.model.mapToLessonUi
import ru.nsu.fit.timetable.presentation.view.LessonTypeUi
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel @Inject constructor(
    private val scheduleInteractor: ScheduleInteractorImpl
) : ViewModel() {

    private var _stateFlow: MutableStateFlow<TimeTableState> =
        MutableStateFlow(TimeTableState(dates = getCurrentWeek()))
    var stateFlow: StateFlow<TimeTableState> = _stateFlow


    init {
        viewModelScope.launch {
            repeat(100) {
                delay(1000)
                Log.d("MY_TAG", it.toString())
            }
        }
    }

    fun processEvent(event: TimeTableEvent) {
        when (event) {
            is TimeTableEvent.OnGetScheduleForDayClick -> getGroupScheduleForDay(
                group = event.group,
                date = event.date
            )
        }
    }

    private fun getGroupScheduleForDay(group: String, date: DateUi) {
        val dates = changeSelectedDate(_stateFlow.value.dates, date)
        _stateFlow.value = _stateFlow.value.copy(loading = true, dates = dates)
        viewModelScope.launch {
            scheduleInteractor.getUserWeekSchedule(group.toInt()).collect {
                _stateFlow.value = _stateFlow.value.copy(
                    loading = false,
                    lessonsUi = it.getDaySchedule(date.dayOfWeek.mapToWeekDay())
                        .map { lesson -> lesson.mapToLessonUi() },
                    dates = dates
                )
            }
        }
    }

    private fun getCurrentWeek(): List<DateUi> {
        val dateFormat = SimpleDateFormat("dd")
        val timeZone = TimeZone.getTimeZone("Russia/Novosibirsk")
        val startOfWeek = Calendar.getInstance(timeZone).apply {
            firstDayOfWeek = Calendar.MONDAY
            set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        }.time
        var dateStartOfWeek = dateFormat.format(startOfWeek).toInt()
        return listOf(
            DateUi(
                dayOfWeek = "Пн",
                numberOfMonth = (dateStartOfWeek++).toString(),
                clickable = true
            ),
            DateUi(dayOfWeek = "Вт", numberOfMonth = (dateStartOfWeek++).toString()),
            DateUi(dayOfWeek = "Ср", numberOfMonth = (dateStartOfWeek++).toString()),
            DateUi(dayOfWeek = "Чт", numberOfMonth = (dateStartOfWeek++).toString()),
            DateUi(dayOfWeek = "Пт", numberOfMonth = (dateStartOfWeek++).toString()),
            DateUi(dayOfWeek = "Сб", numberOfMonth = (dateStartOfWeek++).toString())
        )
    }

    private fun changeSelectedDate(dates: List<DateUi>, currentDate: DateUi): List<DateUi> {
        val updateDates = mutableListOf<DateUi>()
        dates.map {
            if (it == currentDate) {
                updateDates.add(it.copy(clickable = true))
            } else {
                updateDates.add(it.copy(clickable = false))
            }
        }
        return updateDates
    }

    companion object {
        val listLesson = listOf<LessonUi>(
            LessonUi(
                time = "9:00 - 10:35",
                subject = "Мат.Анализ",
                room = "3107",
                typeLesson = LessonTypeUi.Lecture
            ),
            LessonUi(
                time = "10:50 - 12:25",
                subject = "Мат.Анализ",
                room = "3205",
                typeLesson = LessonTypeUi.Seminar
            ),
            LessonUi(
                time = "12:40 - 14:15",
                typeLesson = LessonTypeUi.WindowSchedule
            ),
            LessonUi(
                time = "14:30 - 16:05",
                typeLesson = LessonTypeUi.WindowSchedule
            ),
            LessonUi(
                time = "16:20 - 18:05",
                subject = "Мат.Анализ",
                room = "3205",
                typeLesson = LessonTypeUi.Seminar
            ),
            LessonUi(
                time = "16:20 - 18:05",
                subject = "Мат.Анализ",
                room = "3205",
                typeLesson = LessonTypeUi.Seminar
            ),
        )
        val group = TopBarUi(
            group = "20207",
            month = "Сентябрь",
            date = "21 сентября",
            parityWeek = "Четная",
            fixedSchedule = false
        )
    }
}