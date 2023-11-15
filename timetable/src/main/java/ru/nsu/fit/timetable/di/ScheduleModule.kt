package ru.nsu.fit.timetable.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import ru.nsu.fit.timetable.data.schedule_repository.ScheduleRepositoryImpl
import ru.nsu.fit.timetable.domain.ScheduleRepository

@Module
@InstallIn()
abstract class ScheduleModule {

    @Binds
    abstract fun scheduleRepository(scheduleRepository: ScheduleRepositoryImpl): ScheduleRepository

}