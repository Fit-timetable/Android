package ru.nsu.fit.timetable.domain

import kotlinx.coroutines.flow.Flow
import ru.nsu.fit.timetable.domain.models.LessonForm
import ru.nsu.fit.timetable.domain.models.Rooms
import ru.nsu.fit.timetable.domain.models.WeekSchedule
import java.util.Date
import javax.inject.Inject


class ScheduleInteractorImpl @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) {
    suspend fun getUserWeekSchedule(group: Int): Flow<WeekSchedule> {
        return scheduleRepository.getUserWeekSchedule(group)
    }

    fun getRoomWeekSchedule(roomNumber: String): Flow<WeekSchedule> {
        return scheduleRepository.getRoomWeekSchedule(roomNumber)
    }

    fun getTeacherWeekSchedule(teacherId: Int): Flow<WeekSchedule> {
        return scheduleRepository.getTeacherWeekSchedule(teacherId)
    }

    fun getFreeRoom(date: Date): Flow<Rooms> {
        return scheduleRepository.getFreeRoom(date)
    }

    suspend fun pinScheduleGroup(studentId: Int, group: Int) {
        return scheduleRepository.pinScheduleGroup(studentId, group)
    }

    suspend fun addLesson(lessonForm: LessonForm) {
        return scheduleRepository.addLesson(lessonForm)
    }

    suspend fun resetSchedule(studentId: Int) {
        return scheduleRepository.resetSchedule(studentId)
    }

    suspend fun updateLesson(lessonForm: LessonForm) {
        return scheduleRepository.updateLesson(lessonForm)
    }
}