package ru.nsu.fit.timetable.domain

import kotlinx.coroutines.flow.Flow
import ru.nsu.fit.timetable.domain.models.LessonForm
import ru.nsu.fit.timetable.domain.models.Rooms
import ru.nsu.fit.timetable.domain.models.WeekSchedule
import java.util.Date
import javax.inject.Inject


class ScheduleInteractorImpl @Inject constructor(
    private val scheduleRepository: ScheduleRepository
) : ScheduleInteractor {
    override fun getUserWeekSchedule(groupId : Int): Flow<WeekSchedule> {
        return scheduleRepository.getUserWeekSchedule(groupId)
    }

    override fun getRoomWeekSchedule(roomNumber: String): Flow<WeekSchedule> {
        return scheduleRepository.getRoomWeekSchedule(roomNumber)
    }

    override fun getTeacherWeekSchedule(teacherId: Int): Flow<WeekSchedule> {
        return scheduleRepository.getTeacherWeekSchedule(teacherId)
    }

    override fun getFreeRoom(date: Date): Flow<Rooms> {
        return scheduleRepository.getFreeRoom(date)
    }

    override suspend fun pinScheduleGroup(studentId: Int, group: Int) {
        return scheduleRepository.pinScheduleGroup(studentId, group)
    }

    override suspend fun addLesson(lessonForm: LessonForm) {
        return scheduleRepository.addLesson(lessonForm)
    }

    override suspend fun resetSchedule(studentId: Int) {
        return scheduleRepository.resetSchedule(studentId)
    }

    override suspend fun updateLesson(lessonForm: LessonForm) {
        return scheduleRepository.updateLesson(lessonForm)
    }
}