package ru.nsu.fit.timetable.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nsu.fit.timetable.presentation.TimeTableState
import ru.nsu.fit.timetable.presentation.model.ChoiceGroupUi
import ru.nsu.fit.timetable.presentation.model.ClassUi
import ru.nsu.fit.timetable.presentation.ui.theme.LectureBackGround
import ru.nsu.fit.timetable.presentation.ui.theme.SeminarBackGround
import ru.nsu.fit.timetable.presentation.ui.theme.StaticWhite
import ru.nsu.fit.timetable.presentation.ui.theme.WindowScheduleBackGround

enum class ClassType {
    Lecture,
    Seminar,
    WindowSchedule
}

@Composable
fun ClassBlock(state: TimeTableState) {
    val color = when (state.classUi.typeClass) {
        ClassType.Lecture -> LectureBackGround
        ClassType.Seminar -> SeminarBackGround
        ClassType.WindowSchedule -> WindowScheduleBackGround
    }
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(10.dp)) {
        Box(
            modifier = Modifier.background(color, shape = RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 7.dp)
                    .padding(start = 7.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "1", style = TextStyle(
                        fontSize = 32.sp, color = StaticWhite
                    )
                )
                Text(
                    text = state.classUi.timeStart, style = TextStyle(
                        fontSize = 32.sp, color = StaticWhite
                    )
                )
                Text(
                    text = state.classUi.subject, style = TextStyle(
                        fontSize = 32.sp, color = StaticWhite
                    )
                )
            }
        }
    }

}

@Preview
@Composable
fun ClassBlockBlockPreview() {
    val choiceGroupUi =
        ChoiceGroupUi(
            1, group = "20209", fixedSchedule = true,
            date = "21", month = "октября",
            parityWeek = "четная"
        )
    val classUi =
        ClassUi(
            timeStart = "9:00",
            timeEnd = "10:35",
            subject = "Мат.Анализ",
            auditorium = "3107",
            typeClass = ClassType.Lecture
        )
    val state = TimeTableState(classUi, choiceGroupUi)
    ClassBlock(state)
}