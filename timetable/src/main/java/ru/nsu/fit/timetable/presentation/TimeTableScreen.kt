package ru.nsu.fit.timetable.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.ui.theme.ScreenBackGround
import ru.nsu.fit.timetable.presentation.view.DayOfWeekBlock
import ru.nsu.fit.timetable.presentation.view.LessonBlock
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