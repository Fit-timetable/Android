package ru.nsu.fit.timetable.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.nsu.fit.timetable.presentation.TimeTableState
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.model.LessonUi
import ru.nsu.fit.timetable.presentation.model.TopBarUi
import ru.nsu.fit.timetable.presentation.ui.theme.ScreenBackGround


@Composable
fun TimeTableScreen(
    state: TimeTableState,
    onClickDate: (group: String, date: DateUi) -> Unit,
    onChangeTextWithNumberGroup: (String) -> Unit,
    onClickForward: (group: String, offsetWeek: Int) -> Unit,
    onAddClick: () -> Unit = {}
) {
    Box(modifier = Modifier.background(ScreenBackGround)) {
        Column {
            TopBarBlock(
                state = state,
                onChangeTextWithNumberGroup = onChangeTextWithNumberGroup,
                onAddClick
            )
            DayOfWeekBlock(state = state, onClickDate, onClickForward)
            if (state.error.isEmpty()) {
                LessonBlock(state = state)
            } else {
                ErrorBlock(error = state.error)
            }
        }
    }
}

@Preview
@Composable
fun FullScreenPreview() {
    val state = TimeTableState(
        loading = false,
        error = "",
        lessonsUi = listLesson,
        group = group,
        dates = listOf(DateUi("Пн", "20", false))
    )
    //TimeTableScreen(state = state, onClickDate = { _, _ -> })
}

val listLesson: List<LessonUi>
    get() = listOf<LessonUi>(
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

val dates: List<DateUi>
    get() = listOf(
        DateUi("Пн", "20", false),
        DateUi("Вт", "21", true),
        DateUi("Ср", "22", false),
        DateUi("Чт", "23", false),
        DateUi("Пт", "24", false),
        DateUi("Сб", "25", false),
    )

val group: TopBarUi
    get() = TopBarUi(
        group = "20207",
        month = "Сентябрь",
        date = "21 сентября",
        parityWeek = "Четная",
        fixedSchedule = false
    )