package ru.nsu.fit.timetable.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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

    var sharedFlow: MutableSharedFlow<String> = MutableSharedFlow()

    init {
        onChangeNumberGroup()
    }


    private fun onChangeNumberGroup() {
        sharedFlow
            .filter { it.length == 5 }
            .distinctUntilChanged()
            .debounce(500L)
            .onEach { getGroupScheduleForDay(it, _stateFlow.value.dates.first { date -> date.clickable }) }
            .launchIn(viewModelScope)
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
        var numberLesson = 0
        var currentLessonTime = ""
        viewModelScope.launch {
            scheduleInteractor.getUserWeekSchedule(group.toInt()).collect {
                _stateFlow.value = _stateFlow.value.copy(
                    loading = false,
                    lessonsUi = it.getDaySchedule(date.dayOfWeek.mapToWeekDay())
                        .map { lesson ->
                            if (currentLessonTime != lesson.startTime) {
                                ++numberLesson
                                currentLessonTime = lesson.startTime
                            }
                            lesson.mapToLessonUi(numberLesson)},
                    dates = dates,
                    group = TopBarUi(group = group)
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
                startTime = "9:00 - 10:35",
                subject = "Мат.Анализ",
                room = "3107",
                typeLesson = LessonTypeUi.Lecture
            ),
            LessonUi(
                startTime = "10:50 - 12:25",
                subject = "Мат.Анализ",
                room = "3205",
                typeLesson = LessonTypeUi.Seminar
            ),
            LessonUi(
                startTime = "12:40 - 14:15",
                typeLesson = LessonTypeUi.WindowSchedule
            ),
            LessonUi(
                startTime = "14:30 - 16:05",
                typeLesson = LessonTypeUi.WindowSchedule
            ),
            LessonUi(
                startTime = "16:20 - 18:05",
                subject = "Мат.Анализ",
                room = "3205",
                typeLesson = LessonTypeUi.Seminar
            ),
            LessonUi(
                startTime = "16:20 - 18:05",
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