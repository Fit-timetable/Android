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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nsu.fit.timetable.R
import ru.nsu.fit.timetable.presentation.TimeTableState
import ru.nsu.fit.timetable.presentation.ui.theme.BorderDate
import ru.nsu.fit.timetable.presentation.ui.theme.StaticBlack

@Composable
fun DayOfWeekBlock(state: TimeTableState, oncClick: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)) {
        Icon(painter = painterResource(id = R.drawable.ic_more), contentDescription = "")
        state.dates.forEach { date ->
            Column(
                modifier = Modifier
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp, color = BorderDate,
                            shape = RoundedCornerShape(size = 10.dp)
                        )
                        .clickable { oncClick() }
                )
                {
                    TextOfDateBlock(text = date.dayOfWeek)
                    TextOfDateBlock(text = date.numberOfMonth)
                }
            }
            Icon(painter = painterResource(id = R.drawable.ic_less), contentDescription = "")
        }
    }
}

@Composable
private fun TextOfDateBlock(text: String) {
    Text(
        text = text, style = TextStyle(
            fontSize = 16.sp,
            color = StaticBlack,
        )
    )
}