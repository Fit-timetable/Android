package ru.nsu.fit.timetable.domain

import kotlinx.coroutines.flow.Flow
import ru.nsu.fit.timetable.domain.models.LessonForm
import ru.nsu.fit.timetable.domain.models.Rooms
import ru.nsu.fit.timetable.domain.models.WeekSchedule
import java.util.Date

interface ScheduleRepository {
    fun getUserWeekSchedule(groupId: Int): Flow<WeekSchedule>

    fun getRoomWeekSchedule(roomNumber: String): Flow<WeekSchedule>

    fun getTeacherWeekSchedule(teacherId: Int): Flow<WeekSchedule>

    fun getFreeRoom(date: Date): Flow<Rooms>

    suspend fun pinScheduleGroup(studentId: Int, group: Int)

    suspend fun addLesson(lessonForm: LessonForm)

    suspend fun resetSchedule(studentId: Int)

    suspend fun updateLesson(lessonForm: LessonForm)
}