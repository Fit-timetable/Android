package ru.nsu.fit.timetable.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.model.LessonUi
import ru.nsu.fit.timetable.presentation.model.TopBarUi
import ru.nsu.fit.timetable.presentation.ui.theme.ScreenBackGround
import ru.nsu.fit.timetable.presentation.view.DayOfWeekBlock
import ru.nsu.fit.timetable.presentation.view.LessonBlock
import ru.nsu.fit.timetable.presentation.view.LessonTypeUi
import ru.nsu.fit.timetable.presentation.view.TopBarBlock


@Composable
fun TimeTableScreen(state: TimeTableState, onClickDate: (group: String, date: DateUi) -> Unit) {
    Box(modifier = Modifier.background(ScreenBackGround)) {
        Column {
            TopBarBlock(state = state)
            DayOfWeekBlock(state = state, onClickDate)
            LessonBlock(state = state)
        }
    }
}

@Preview
@Composable
fun FullScreenPreview() {
    val state = TimeTableState(
        loading = false,
        error = null,
        lessonsUi = listLesson,
        group = group,
        dates = listOf(DateUi("Пн", "20", false))
    )
    TimeTableScreen(state = state, onClickDate = { _, _ -> })
}

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