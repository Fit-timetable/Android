package ru.nsu.fit.auth.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nsu.fit.auth.data.auth.AuthRepositoryImpl
import ru.nsu.fit.auth.data.register.RegisterRepositoryImpl
import ru.nsu.fit.auth.domain.AuthRepository
import ru.nsu.fit.auth.domain.RegisterRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthModule {

    @Binds
    abstract fun authRepository(
        scheduleRepository: AuthRepositoryImpl
    ): AuthRepository


    @Binds
    abstract fun registerRepository(
        scheduleRepository: RegisterRepositoryImpl
    ): RegisterRepository

}