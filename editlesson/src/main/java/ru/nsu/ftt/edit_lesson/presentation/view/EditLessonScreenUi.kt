@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package ru.nsu.ftt.edit_lesson.presentation.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nsu.fit.common.models.LessonParity
import ru.nsu.fit.common.models.LessonType
import ru.nsu.fit.common.models.WeekDay
import ru.nsu.fit.common.models.mapToLessonParity
import ru.nsu.fit.common.models.mapToLessonType
import ru.nsu.fit.common.models.mapToWeekDay
import ru.nsu.ftt.edit_lesson.data.remote_repository.model.lessonDateRemote
import ru.nsu.ftt.edit_lesson.data.remote_repository.model.placeRemote
import ru.nsu.ftt.edit_lesson.presentation.theme.FTTTheme
import ru.nsu.ftt.edit_lesson.presentation.theme.ScreenBackGround
import ru.nsu.ftt.edit_lesson.presentation.theme.TextFieldColor

val TIME_LESSON = listOf(
    "9:00",
    "10:50",
    "12:40",
    "14:30",
    "16:20",
    "18:10",
    "20:00",
)

val LIST_LESSONS = listOf(
    "Физкультура и спорт",
    "Дискрет. матем.",
    "Основы ООП",
    "ЭВМ и ПУ",
    "Физика 2",
    "ЛОП",
    "Ин. яз.",
    "ДГМА",
    "Мат.анализ",
    "Программирование",
    "Алгебра и геометрия",
    "Информатика",
    "Основы рос. гос-ти",
    "МЛиТА",
    "История России",
    "Алг. и структ. данн.",
    "Учеб. практика, НИР",
    "Базы данных",
    "ООАиД",
    "Операц. системы",
    "Мет. трансл. компил.",
    "Электротех. и элект.",
    "Вычисл. математика",
    "Когнит. психол.",
    "Сети и телекоммун.",
    "Практ. инф. без-ть",
    "Разр. прил. Android",
    "ВРМП",
    "ИСИС",
    "ИАД",
    "Парадигмы программ.",
    "Информационный поиск",
    "Стандартизация прогр",
    "КИТ",
    "КРПО",
    "ИЭиТП",
    "С# и платф.NET",
    "Защита информ.",
)

@Composable
fun EditLesson(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(ScreenBackGround),
    ) {

    }
}

@Composable
fun CreateLesson(
    modifier: Modifier = Modifier,
    state: EditLessonState = EditLessonState(),
    saveLesson: (LessonForm) -> Unit = {},
    cancel: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .width(IntrinsicSize.Max)
            .background(ScreenBackGround)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 25.dp),
    ) {
        Text(
            modifier = modifier.align(Alignment.CenterHorizontally), text = "Создать пару"
        )
        var lesson by remember { mutableStateOf("") }
        var type by remember { mutableStateOf(LessonType.LECTURE) }
        var dayOfWeek by remember { mutableStateOf(WeekDay.MONDAY) }
        var parity by remember { mutableStateOf(LessonParity.EVEN) }
        var timeStart by remember { mutableStateOf("") }
        var room by remember { mutableStateOf("") }
        var googleMeetLink by remember { mutableStateOf("") }
        ParamBlock(paramName = "Предмет") {
            StringPickBlock(
                modifier,
                LIST_LESSONS,
                0,
                isError = state.error == InputError.LESSON,
            ) {
                lesson = it
            }
        }
        ParamBlock(paramName = "Тип") {
            StringPickBlock(
                Modifier,
                LessonType.values()
                    .toList()
                    .filter { it != LessonType.WINDOW }
                    .map(LessonType::mapToRus),
                0,
                isError = state.error == InputError.TYPE
            ) {
                type = it.mapToLessonType()
            }
        }
        ParamBlock(paramName = "День недели") {
            StringPickBlock(
                Modifier,
                WeekDay.values().toList().map(WeekDay::mapToRus),
                0,
                isError = state.error == InputError.DAY_OF_WEEK
            ) {
                dayOfWeek = it.mapToWeekDay()
            }
        }
        ParamBlock(paramName = "День недели") {
            StringPickBlock(
                Modifier,
                LessonParity.values().toList().map(LessonParity::mapToRus),
                0,
                isError = state.error == InputError.PARITY
            ) {
                parity = it.mapToLessonParity()
            }
        }
        ParamBlock(paramName = "Время начала") {
            StringPickBlock(
                Modifier,
                TIME_LESSON,
                0,
                isError = state.error == InputError.TIME_START
            ) {
                timeStart = it
            }
        }
        ParamBlock(paramName = "Кабинет") {
            StringInputBlock(Modifier, isError = state.error == InputError.ROOM) {
                room = it
            }
        }
        ParamBlock(paramName = "Meet link") {
            StringInputBlock(Modifier, isError = state.error == InputError.LINK) {
                googleMeetLink = it
            }
        }
        Row(
            Modifier
                .padding(top = 22.dp)
                .width(IntrinsicSize.Max)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            FttButton(
                Modifier
                    .height(50.dp)
                    .padding(end = 30.dp), onClick = {
                    saveLesson(
                        LessonForm(
                            subjectName = lesson,
                            type = type,
                            date = lessonDateRemote {
                                weekDay = dayOfWeek
                                startTime = timeStart
                            },
                            place = placeRemote {
                                meetLink = googleMeetLink
                                this.room = room
                            },
                            parity = parity,
                        )
                    )
                }, text = "Сохранить"
            )
            FttButton(Modifier.height(50.dp), onClick = { cancel() }, text = "Не сохранять")
        }
    }
}

@Composable
fun ColumnScope.ParamBlock(
    modifier: Modifier = Modifier,
    paramName: String,
    inputBlock: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .align(Alignment.CenterHorizontally)
            .padding(top = 11.dp)
    ) {
        Column {
            Text(modifier = modifier.padding(top = 11.dp), text = paramName)
            inputBlock()
        }
    }
}

@Composable
fun StringInputBlock(
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    onTextChanged: (String) -> Unit,
) {
    TextField(
        modifier = modifier,
        value = "",
        isError = isError,
        onValueChange = { onTextChanged(it) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = TextFieldColor,
            unfocusedContainerColor = TextFieldColor,
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StringPickBlock(
    modifier: Modifier = Modifier,
    listData: List<String>,
    indexSelected: Int,
    isError: Boolean = false,
    onTextChanged: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(listData[indexSelected]) }
    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            isError = isError,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = modifier
                .menuAnchor(),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = TextFieldColor,
                unfocusedContainerColor = TextFieldColor,
            )
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier,
        ) {
            listData.forEach { item ->
                DropdownMenuItem(text = { Text(text = item) }, onClick = {
                    selectedText = item
                    expanded = false
                    onTextChanged(item)
                })
            }
        }
    }
}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun StringInputPickBlock(
//    modifier: Modifier = Modifier,
//    listData: List<String>,
//    onItemChanged: (Subject) -> Unit,
//    onTextChanged: (String) -> Unit,
//    initText: String,
//    isError: Boolean = false,
//) {
//    var expanded by remember { mutableStateOf(false) }
//    ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
//        TextField(
//            value = initText,
//            onValueChange = {
//                onTextChanged(it)
//            },
//            readOnly = false,
//            isError = isError,
//            modifier = modifier
//                .menuAnchor(),
//            colors = TextFieldDefaults.colors(
//                focusedContainerColor = TextFieldColor,
//                unfocusedContainerColor = TextFieldColor,
//            ),
//        )
//
//        ExposedDropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false },
//            modifier = Modifier,
//        ) {
//            listData.forEach { item ->
//                DropdownMenuItem(text = { Text(text = item) }, onClick = {
//                    expanded = false
//                    onItemChanged(item)
//                })
//            }
//        }
//    }
//}

@Composable
fun FttButton(
    modifier: Modifier = Modifier, onClick: () -> Unit, text: String
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        border = BorderStroke(1.dp, Color.Black),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66AFAF))
    ) {
        FttButtonText(
            Modifier, text
        )
    }
}

@Composable
fun FttButtonText(
    modifier: Modifier = Modifier, text: String = ""
) {
    Text(
        text = text, modifier = modifier, color = Color.Black
    )
}

@Composable
@Preview
fun PreviewFttButton() {
    FTTTheme {
        FttButton(
            modifier = Modifier,
            {},
            "asdasdasd"
        )
    }
}

@Composable
@Preview
fun PreviewEditLesson() {
    FTTTheme {
        EditLesson()
    }
}

@Composable
@Preview
fun PreviewCreateLesson() {
    FTTTheme {
        CreateLesson(Modifier)
    }
}