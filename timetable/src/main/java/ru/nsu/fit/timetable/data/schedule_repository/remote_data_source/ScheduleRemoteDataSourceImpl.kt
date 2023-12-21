package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import ru.nsu.fit.timetable.domain.models.WeekSchedule
import javax.inject.Inject

class ScheduleRemoteDataSourceImpl @Inject constructor(
    private val scheduleService: ScheduleService
) : ScheduleRemoteDataSource {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getUserWeekSchedule(group: Int): Flow<WeekSchedule> {
        return flow {
            emit(scheduleService.getSchedule(group))
        }
            .map { it.mapToWeekSchedule() }
            .catch { error ->
                if (error is retrofit2.HttpException) {
                    throw error
                }
                Log.d("error", error.toString())
            }
    }
}