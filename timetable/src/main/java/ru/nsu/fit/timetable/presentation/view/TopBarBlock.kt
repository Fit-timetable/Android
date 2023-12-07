package ru.nsu.fit.timetable.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nsu.fit.timetable.R
import ru.nsu.fit.timetable.presentation.TimeTableState
import ru.nsu.fit.timetable.presentation.ui.theme.StaticBlack
import ru.nsu.fit.timetable.presentation.ui.theme.StaticWhite
import ru.nsu.fit.timetable.presentation.ui.theme.TopBarBackGround

@Composable
fun TopBarBlock(state: TimeTableState, onChangeTextWithNumberGroup: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(TopBarBackGround)
    ) {
        Row() {
            Column {
                if (state.group.group != null) {
                    GroupBlock(group = state.group.group, onChangeTextWithNumberGroup)
                }
                DateBlock(state = state)
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Row {
                    IconPinScheduleBlock()
                    CalendarBlock()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupBlock(group: String, onValueChange: (String) -> Unit) {
    val message = remember { mutableStateOf(group) }
        Row(modifier = Modifier.padding(start = 22.dp, top = 9.dp)) {
            TextField(
                modifier = Modifier.width(100.dp),
                value = message.value,
                onValueChange = { text ->
                    message.value = text
                    onValueChange(text)
                },
                textStyle = TextStyle(fontSize = 18.sp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor =  StaticWhite,
                    unfocusedIndicatorColor = TopBarBackGround,
                    focusedTextColor =  StaticWhite,
                    unfocusedTextColor= StaticWhite,
                    containerColor  = TopBarBackGround,
                    cursorColor = StaticWhite)
            )
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "image description")
        }
}

@Composable
fun DateBlock(state: TimeTableState) {
    Row(modifier = Modifier.padding(start = 22.dp)) {
        Text(text = state.group.date, style = TextStyle(fontSize = 17.sp))
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = state.group.month,
            style = TextStyle(fontSize = 17.sp)
        )
        Text(
            modifier = Modifier.padding(start = 5.dp),
            text = state.group.parityWeek,
            style = TextStyle(fontSize = 17.sp)
        )
    }
}

@Composable
fun IconPinScheduleBlock() {
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
            text = stringResource(id = R.string.icon_pin_schedule)
        )
    }
}

@Composable
fun CalendarBlock() {
    Image(
        modifier = Modifier.padding(start = 9.dp, end = 9.dp),
        painter = painterResource(id = R.drawable.ic_date),
        contentDescription = "image description"
    )
}

@Preview
@Composable
fun TopBarBlockPreview() {
//    val choiceGroupUi =
//        ChoiceGroupUi(
//            1, group = "20209", fixedSchedule = true,
//            date = "21", month = "октября",
//            parityWeek = "четная"
//        )
//    val lessonUi =
//        LessonUi(
//            time = "9:00- 10:35",
//            subject = "Мат.Анализ",
//            auditorium = "3107",
//            typeClass = LessonType.Lecture
//        )
//    val state = TimeTableState(lessonUi, choiceGroupUi)
//    TopBarBlock(state)
}