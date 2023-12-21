package ru.nsu.fit.timetable.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nsu.fit.timetable.R
import ru.nsu.fit.timetable.presentation.TimeTableState
import ru.nsu.fit.timetable.presentation.ui.theme.StaticWhite
import ru.nsu.fit.timetable.presentation.ui.theme.TopBarBackGround

@Composable
fun TopBarBlock(
    state: TimeTableState,
    onChangeTextWithNumberGroup: (String) -> Unit,
    onAddClick: () -> Unit = {}
) {
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
                    IconAddLessonBlock(onAddClick)
//                    IconPinScheduleBlock()
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
                focusedIndicatorColor = StaticWhite,
                unfocusedIndicatorColor = TopBarBackGround,
                focusedTextColor = StaticWhite,
                unfocusedTextColor = StaticWhite,
                containerColor = TopBarBackGround,
                cursorColor = StaticWhite
            )
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
fun IconAddLessonBlock(onClickListener: () -> Unit = {}) {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Изменить",
            modifier = Modifier
                .size(54.dp)
                .padding(all = 8.dp)
                .clickable { onClickListener() },
        )
    }
}

@Composable
fun IconPinScheduleBlock() {
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    )
    {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Изменить",
            modifier = Modifier
                .size(50.dp)
                .padding(all = 8.dp),
        )
    }
}

@Composable
fun CalendarBlock() {
    Image(
        modifier = Modifier.padding(all = 6.dp),
        painter = painterResource(id = R.drawable.ic_date),
        contentDescription = "image description"
    )
}

@Preview
@Composable
fun TopBarBlockPreview() {
    val state = TimeTableState()
    TopBarBlock(state, {}, {})
}