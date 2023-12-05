package ru.nsu.fit.timetable.data.schedule_repository

import kotlinx.coroutines.flow.Flow
import ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.ScheduleRemoteDataSource
import ru.nsu.fit.timetable.domain.ScheduleRepository
import ru.nsu.fit.timetable.domain.models.LessonForm
import ru.nsu.fit.timetable.domain.models.Rooms
import ru.nsu.fit.timetable.domain.models.WeekSchedule
import java.util.Date
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val remoteDataSource: ScheduleRemoteDataSource
): ScheduleRepository {
    override suspend fun getUserWeekSchedule(group: Int): Flow<WeekSchedule> {
        return remoteDataSource.getUserWeekSchedule(group)
    }

    override fun getRoomWeekSchedule(roomNumber: String): Flow<WeekSchedule> {
        TODO("Not yet implemented")
    }

    override fun getTeacherWeekSchedule(teacherId: Int): Flow<WeekSchedule> {
        TODO("Not yet implemented")
    }

    override fun getFreeRoom(date: Date): Flow<Rooms> {
        TODO("Not yet implemented")
    }

    override suspend fun pinScheduleGroup(studentId: Int, group: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun addLesson(lessonForm: LessonForm) {
        TODO("Not yet implemented")
    }

    override suspend fun resetSchedule(studentId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun updateLesson(lessonForm: LessonForm) {
        TODO("Not yet implemented")
    }
}