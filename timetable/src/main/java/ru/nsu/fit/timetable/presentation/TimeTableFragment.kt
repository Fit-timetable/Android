package ru.nsu.fit.timetable.presentation


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.model.TopBarUi
import ru.nsu.fit.timetable.presentation.model.LessonUi
import ru.nsu.fit.timetable.presentation.view.LessonType

class TimeTableFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val state = TimeTableState(lessonsUi = listLesson, group = group, dates = dates)
                TimeTableScreen(state =state)
            }
        }
    }

    companion object {
        val listLesson = listOf<LessonUi>(
            LessonUi(
                time = "9:00 - 10:35",
                subject = "Мат.Анализ",
                auditorium = "3107",
                typeClass = LessonType.Lecture
            ),
            LessonUi(
                time = "10:50 - 12:25",
                subject = "Мат.Анализ",
                auditorium = "3205",
                typeClass = LessonType.Seminar
            ),
            LessonUi(
                time = "12:40 - 14:15",
                typeClass = LessonType.WindowSchedule
            ),
            LessonUi(
                time = "14:30 - 16:05",
                typeClass = LessonType.WindowSchedule
            ),
            LessonUi(
                time = "16:20 - 18:05",
                subject = "Мат.Анализ",
                auditorium = "3205",
                typeClass = LessonType.Seminar
            )
        )
        val group = TopBarUi(
            id = 1,
            group = "20207",
            month = "Сентябрь",
            date = "21 сентября",
            parityWeek = "Четная",
            fixedSchedule = false
        )
        val dates = listOf<DateUi>(
            DateUi(dayOfWeek = "Пн", numberOfMonth = "1"),
            DateUi(dayOfWeek = "Вт", numberOfMonth = "2"),
            DateUi(dayOfWeek = "Ср", numberOfMonth = "3"),
            DateUi(dayOfWeek = "Чт", numberOfMonth = "4"),
            DateUi(dayOfWeek = "Пт", numberOfMonth = "5"),
            DateUi(dayOfWeek = "Сб", numberOfMonth = "6")
        )
    }
}
