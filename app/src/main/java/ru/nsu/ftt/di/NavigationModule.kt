package ru.nsu.ftt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nsu.fit.common.FttRouter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {

    @Singleton
    @Provides
    fun provideFttRouter(): FttRouter {
        return FttRouter()
    }
}