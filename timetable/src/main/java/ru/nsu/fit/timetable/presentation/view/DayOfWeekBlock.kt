package ru.nsu.fit.timetable.presentation.view

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nsu.fit.timetable.R
import ru.nsu.fit.timetable.presentation.TimeTableState
import ru.nsu.fit.timetable.presentation.model.DateUi
import ru.nsu.fit.timetable.presentation.ui.theme.BackGroundDate
import ru.nsu.fit.timetable.presentation.ui.theme.ScreenBackGround
import ru.nsu.fit.timetable.presentation.ui.theme.StaticBlack
import ru.nsu.fit.timetable.presentation.ui.theme.StaticWhite

@Composable
fun DayOfWeekBlock(
    state: TimeTableState,
    oncClick: (group: String, date: DateUi) -> Unit,
    onClickOffsetWeek: (group: String, offsetWeek: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 13.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .clickable {
                    state.group.group?.let {
                        onClickOffsetWeek(
                            it,
                            -7
                        )
                    }
                },
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "image description"
        )
        state.dates.forEach { date ->
            var backGround = ScreenBackGround
            var textColor = StaticBlack
            if (date.clickable) {
                backGround = BackGroundDate
                textColor = StaticWhite
            }
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .border(
                        width = 1.dp, color = BackGroundDate,
                        shape = RoundedCornerShape(size = 10.dp)
                    )
                    .background(backGround, shape = RoundedCornerShape(size = 10.dp))
                    .clickable { state.group.group?.let { oncClick(it, date) } }
            )
            {
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .padding(horizontal = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    TextOfDateBlock(text = date.dayOfWeek, textColor)
                    TextOfDateBlock(text = date.numberOfMonth, textColor)
                }
            }
        }
        Icon(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .clickable {
                    state.group.group?.let {
                        onClickOffsetWeek(
                            it,
                            7
                        )
                    }
                },
            painter = painterResource(id = R.drawable.ic_forward),
            contentDescription = "image description"
        )
    }
}

@Composable
private fun TextOfDateBlock(text: String, color: Color) {
    Text(
        text = text, style = TextStyle(
            fontSize = 15.sp,
            color = color,
        )
    )
}

@Composable
@Preview
fun DayOfWeekBlockPrewie() {
    val state = TimeTableState(
        loading = false,
        error = null,
        lessonsUi = listLesson,
        group = group,
        dates = dates
    )
    // DayOfWeekBlock(state = state){}
}