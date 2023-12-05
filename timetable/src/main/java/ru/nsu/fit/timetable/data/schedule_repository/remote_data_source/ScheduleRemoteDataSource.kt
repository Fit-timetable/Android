package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source

import kotlinx.coroutines.flow.Flow
import ru.nsu.fit.timetable.domain.models.WeekSchedule

interface ScheduleRemoteDataSource {
    suspend fun getUserWeekSchedule(group: Int): Flow<WeekSchedule>
}
