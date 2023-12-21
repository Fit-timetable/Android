package ru.nsu.fit.common

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class FttRouter {

    private val _currentScreen = MutableSharedFlow<FttScreens>(extraBufferCapacity = 6)
    val currentScreen: SharedFlow<FttScreens>
        get() = _currentScreen

    private val _toastFlow = MutableSharedFlow<String>(extraBufferCapacity = 6)
    val toastFlow: SharedFlow<String>
        get() = _toastFlow

    suspend fun openAuthScreen() {
        _currentScreen.emit(FttScreens.AuthScreen)
    }

    suspend fun openScheduleScreen() {
        _currentScreen.emit(FttScreens.ScheduleScreen)
    }

    suspend fun openRegisterScreen() {
        _currentScreen.emit(FttScreens.RegisterScreen)
    }

    suspend fun back() {
        _currentScreen.emit(FttScreens.Back)
    }

    suspend fun openCreateLessonScreen() {
        _currentScreen.emit(FttScreens.CreateLessonScreen)
    }


    fun sendToast(message: String) {
        _toastFlow.tryEmit(message)
    }
}
