package ru.nsu.fit.timetable.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import ru.nsu.fit.timetable.presentation.ui.theme.CardBackGround
import ru.nsu.fit.timetable.presentation.ui.theme.LectureBackGround
import ru.nsu.fit.timetable.presentation.ui.theme.SeminarBackGround
import ru.nsu.fit.timetable.presentation.ui.theme.StaticBlack
import ru.nsu.fit.timetable.presentation.ui.theme.StaticWhite
import ru.nsu.fit.timetable.presentation.ui.theme.WindowScheduleBackGround

enum class LessonTypeUi {
    Lecture,
    Seminar,
    WindowSchedule
}

@Composable
fun LessonBlock(state: TimeTableState) {
    LazyColumn(
        modifier = Modifier.padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (state.lessonsUi == null) return@LazyColumn

        itemsIndexed(state.lessonsUi) { index, lesson ->
            val color = when (lesson.typeLesson) {
                LessonTypeUi.Lecture -> LectureBackGround
                LessonTypeUi.Seminar -> SeminarBackGround
                LessonTypeUi.WindowSchedule -> WindowScheduleBackGround
            }
            Card(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
                    .fillMaxWidth()
                    .height(110.dp)
                    .background(CardBackGround),
                shape = RoundedCornerShape(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            CardBackGround,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Box(
                        modifier = Modifier.background(
                            color,
                            shape = RoundedCornerShape(10.dp)
                        ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(vertical = 7.dp, horizontal = 7.dp)
                                .height(110.dp)
                                .width(110.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = index.toString(), style = TextStyle(
                                    fontSize = 23.sp, color = StaticWhite
                                )
                            )
                            Text(
                                text = lesson.time, style = TextStyle(
                                    fontSize = 12.sp, color = StaticWhite
                                )
                            )

                            when (lesson.typeLesson) {
                                LessonTypeUi.Lecture -> TypeLessonBlock(type = stringResource(id = R.string.type_lesson_lecture))
                                LessonTypeUi.Seminar -> TypeLessonBlock(type = stringResource(id = R.string.type_lesson_seminar))
                                LessonTypeUi.WindowSchedule -> {}
                            }
                        }
                    }
                    if (lesson.typeLesson != LessonTypeUi.WindowSchedule) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    CardBackGround,
                                    shape = RoundedCornerShape(10.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(start = 15.dp),
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = lesson.subject, style = TextStyle(
                                        fontSize = 10.sp,
                                        color = StaticBlack
                                    )
                                )
                                Text(
                                    text = lesson.room, style = TextStyle(
                                        fontSize = 20.sp,
                                        color = StaticBlack
                                    )
                                )
                            }
                            Column(modifier = Modifier.fillMaxSize()) {
                                if (lesson.room == "Google Meet") {
                                    Box(
                                        modifier = Modifier.fillMaxSize(),
                                        contentAlignment = Alignment.TopEnd
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.ic_meting),
                                            contentDescription = "image description"
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.BottomEnd
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_infromation),
                                        contentDescription = "image description"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TypeLessonBlock(type: String) {
    Text(
        text = type, style = TextStyle(
            fontSize = 20.sp, color = StaticWhite
        )
    )
}

@Preview
@Composable
fun ClassBlockBlockPreview() {
//    val choiceGroupUi =
//        ChoiceGroupUi(
//            1, group = "20209", fixedSchedule = true,
//            date = "21", month = "октября",
//            parityWeek = "четная"
//        )
//    val lessonUi =
//        LessonUi(
//            time = "9:00 - 10:35",
//            subject = "Мат.Анализ",
//            auditorium = "3107",
//            typeClass = LessonType.Lecture
//        )
//    val state = TimeTableState(lessonUi, choiceGroupUi)
//    LessonBlock(state)
}