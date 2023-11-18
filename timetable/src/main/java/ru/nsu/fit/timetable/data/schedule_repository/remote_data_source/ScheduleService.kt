package ru.nsu.fit.timetable.data.schedule_repository.remote_data_source

import retrofit2.http.GET
import retrofit2.http.Path
import ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.models.WeekScheduleRemote

interface ScheduleService {
    @GET("/schedule/group/{group}")
    suspend fun getSchedule(@Path("group") group: Int): WeekScheduleRemote
}