package ru.nsu.fit.auth.presentation.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.nsu.fit.auth.domain.AuthInteractor
import ru.nsu.fit.common.FttRouter
import ru.nsu.fit.common.runCatchingNonCancellation
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val interactor: AuthInteractor,
    private val router: FttRouter,
) : ViewModel() {

    private var _stateFlow: MutableStateFlow<RegisterState> =
        MutableStateFlow(RegisterState())
    var stateFlow: StateFlow<RegisterState> = _stateFlow
    private val state = _stateFlow.value


    fun sendRequest(mail: String) {
        viewModelScope.launch {
            runCatchingNonCancellation {
                _stateFlow.emit(state.copy(loading = true, error = null))
                interactor.requestSignup(mail)
                _stateFlow.emit(
                    state.copy(
                        loading = false,
                        error = null,
                        requestLoginSuccess = true
                    )
                )
                Log.d("FTT_TAG", "request signup success")
            }.onFailure {
                Log.d("FTT_TAG", "error loading ${it.stackTraceToString()}")
                router.sendToast("Ошибка запроса регистрации")
                _stateFlow.emit(state.copy(loading = false, error = "Can't connect to host"))
            }
        }
    }

    fun register(mail: String, password: String, code: String) {
        viewModelScope.launch {
            runCatchingNonCancellation {
                _stateFlow.emit(state.copy(loading = true, error = null))
                interactor.confirmSignup(mail, password, code.toInt())
                _stateFlow.emit(
                    state.copy(
                        loading = false,
                        error = null,
                        requestLoginSuccess = true
                    )
                )
                Log.d("FTT_TAG", "register success")
                router.openAuthScreen()
            }.onFailure {
                Log.d("FTT_TAG", "error loading ${it.stackTraceToString()}")
                router.sendToast("Ошибка регистрации")
                _stateFlow.emit(state.copy(loading = false, error = "Can't connect to host"))
            }
        }
    }
}