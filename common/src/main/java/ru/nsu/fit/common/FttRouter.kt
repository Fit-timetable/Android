package ru.nsu.fit.common

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class FttRouter {

    private val _currentScreen = MutableSharedFlow<FttScreens>()
    val currentScreen: SharedFlow<FttScreens>
        get() = _currentScreen

    suspend fun openAuthScreen() {
        _currentScreen.emit(FttScreens.AuthScreen)
    }

    suspend fun openScheduleScreen() {
        _currentScreen.emit(FttScreens.ScheduleScreen)
    }

    suspend fun openRegisterScreen() {
        _currentScreen.emit(FttScreens.RegisterScreen)
    }
}
