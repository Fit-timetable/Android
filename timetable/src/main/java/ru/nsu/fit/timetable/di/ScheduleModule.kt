package ru.nsu.fit.timetable.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ru.nsu.fit.timetable.data.schedule_repository.ScheduleRepositoryImpl
import ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.ScheduleRemoteDataSource
import ru.nsu.fit.timetable.data.schedule_repository.remote_data_source.ScheduleRemoteDataSourceImpl
import ru.nsu.fit.timetable.domain.ScheduleRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class ScheduleModule {

    @Binds
    abstract fun scheduleRepository(
        scheduleRepository: ScheduleRepositoryImpl
    ): ScheduleRepository

    @Binds
    abstract fun scheduleRemoteDataSource(
        scheduleRemoteDataSourceImpl: ScheduleRemoteDataSourceImpl
    ): ScheduleRemoteDataSource
}