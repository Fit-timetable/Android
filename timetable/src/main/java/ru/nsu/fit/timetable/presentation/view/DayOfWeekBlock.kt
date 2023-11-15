package ru.nsu.fit.timetable.presentation.view

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nsu.fit.timetable.presentation.TimeTableState
import ru.nsu.fit.timetable.presentation.ui.theme.BorderDate
import ru.nsu.fit.timetable.presentation.ui.theme.StaticBlack

@Composable
fun DayOfWeekBlock(state: TimeTableState, oncClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        state.dates.forEach { date ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .border(
                        width = 1.dp, color = BorderDate,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .clickable { oncClick() }
            )
            {
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextOfDateBlock(text = date.dayOfWeek)
                    TextOfDateBlock(text = date.numberOfMonth)
                }
            }
        }
    }
}

@Composable
private fun TextOfDateBlock(text: String) {
    Text(
        text = text, style = TextStyle(
            fontSize = 20.sp,
            color = StaticBlack,
        )
    )
}