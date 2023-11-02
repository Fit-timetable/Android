package ru.nsu.fit.timetable.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nsu.fit.timetable.R
import ru.nsu.fit.timetable.presentation.TimeTableState
import ru.nsu.fit.timetable.presentation.model.ChoiceGroupUi
import ru.nsu.fit.timetable.presentation.model.ClassUi
import ru.nsu.fit.timetable.presentation.ui.theme.StaticBlack
import ru.nsu.fit.timetable.presentation.ui.theme.TopBarBackGround

@Composable
fun TopBarBlock(state: TimeTableState) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(TopBarBackGround)
    ) {
        Row() {
            Column {
                Row(modifier = Modifier.padding(start = 22.dp, top = 9.dp)) {
                    Text(text = state.group.group, style = TextStyle(fontSize = 18.sp))
                    Icon(Icons.Filled.ArrowDropDown, contentDescription = "image description")
                }
                Row(modifier = Modifier.padding(start = 22.dp)) {
                    Text(text = state.group.date, style = TextStyle(fontSize = 12.sp))
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = state.group.month,
                        style = TextStyle(fontSize = 12.sp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = state.group.parityWeek,
                        style = TextStyle(fontSize = 12.sp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row() {
                    Box(
                        modifier = Modifier.border(
                            width = 1.dp,
                            color = StaticBlack,
                            shape = RoundedCornerShape(size = 10.dp),
                        ),
                        contentAlignment = Alignment.Center
                    )
                    {
                        Text(
                            modifier = Modifier.padding(all = 7.dp),
                            text = "Закрепить\nрасписание"
                        )
                    }
                    Image(
                        modifier = Modifier.padding(start = 9.dp, end = 9.dp),
                        painter = painterResource(id = R.drawable.ic_date),
                        contentDescription = "image description"
                    )
                }

            }
        }

    }
}

@Preview
@Composable
fun TopBarBlockPreview() {
    val choiceGroupUi =
        ChoiceGroupUi(
            1, group = "20209", fixedSchedule = true,
            date = "21", month = "октября",
            parityWeek = "четная"
        )
    val classUi =
        ClassUi(timeStart = "9:00", timeEnd = "10:35", subject = "Мат.Анализ", auditorium = "3107", typeClass = ClassType.Lecture)
    val state = TimeTableState(classUi, choiceGroupUi)
    TopBarBlock(state)
}