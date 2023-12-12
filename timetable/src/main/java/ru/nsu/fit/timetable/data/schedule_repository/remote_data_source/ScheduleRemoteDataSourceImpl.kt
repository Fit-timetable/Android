package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.nsu.fit.timetable.domain.models.WeekSchedule
import javax.inject.Inject

class ScheduleRemoteDataSourceImpl @Inject constructor(
    private val scheduleService: ScheduleService
) : ScheduleRemoteDataSource {
    override suspend fun getUserWeekSchedule(group: Int): Flow<WeekSchedule> {
        return flow {
            emit(scheduleService.getSchedule(group))
        }
            .flowOn(Dispatchers.IO)
            .map { it.mapToWeekSchedule() }
    }
}