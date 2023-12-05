package ru.nsu.fit.timetable.presentation.view

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.nsu.fit.timetable.R
import ru.nsu.fit.timetable.presentation.TimeTableState
import ru.nsu.fit.timetable.presentation.model.LessonUi
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
fun LessonBlock(
    modifier: Modifier = Modifier,
    state: TimeTableState,
    onCardClick: (LessonUi) -> Unit = { _ -> }
) = LazyColumn(
    modifier = Modifier
        .padding(horizontal = 10.dp)
        .animateContentSize(),
    verticalArrangement = Arrangement.spacedBy(5.dp)
) {
    itemsIndexed(state.lessonsUi) { index, lesson ->
        val color = when (lesson.typeLesson) {
            LessonTypeUi.Lecture -> LectureBackGround
            LessonTypeUi.Seminar -> SeminarBackGround
            LessonTypeUi.WindowSchedule -> WindowScheduleBackGround
        }
        LessonBackgroundCard(
            modifier = modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = color),
            ) { onCardClick(lesson) }
        ) {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {
                LessonUiTimeInfo(
                    modifier
                        .fillParentMaxHeight()
                        .background(color),
                    lesson,
                )
                if (lesson.typeLesson == LessonTypeUi.WindowSchedule) return@Row
                LessonUiInfo(modifier, lesson)
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

@Composable
fun LessonBackgroundCard(
    modifier: Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .height(IntrinsicSize.Min)
            .fillMaxWidth()
            .height(110.dp)
            .graphicsLayer {
                clip = true
                shape = RoundedCornerShape(10.dp)
            },
        colors = CardDefaults.cardColors(containerColor = CardBackGround),
        content = content,
    )
}

@Composable
fun LessonUiInfo(modifier: Modifier, lesson: LessonUi) {
    Column(
        modifier = modifier
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
}


@Composable
fun LessonUiTimeInfo(modifier: Modifier = Modifier, lesson: LessonUi) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 7.dp, horizontal = 7.dp)
                .height(110.dp)
                .width(110.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = lesson.indexInDay.toString(), style = TextStyle(
                    fontSize = 33.sp, color = StaticWhite,
                )
            )
            Text(
                text = lesson.time, style = TextStyle(
                    fontSize = 16.sp, color = StaticWhite
                )
            )

            when (lesson.typeLesson) {
                LessonTypeUi.Lecture -> TypeLessonBlock(type = stringResource(id = R.string.type_lesson_lecture))
                LessonTypeUi.Seminar -> TypeLessonBlock(type = stringResource(id = R.string.type_lesson_seminar))
                LessonTypeUi.WindowSchedule -> {}
            }
        }
    }
}


@Composable
fun TypeLessonBlock(type: String) {
    Text(
        text = type,
        style = TextStyle(
            fontSize = 20.sp, color = StaticWhite
        )
    )
}

@Preview
@Composable
fun LessonBlockPreview() {
    val state = TimeTableState(
        loading = false,
        error = null,
        lessonsUi = listLesson,
        group = group,
        dates = dates
    )
    LessonBlock(state = state)
}