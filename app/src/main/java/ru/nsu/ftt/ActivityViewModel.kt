package ru.nsu.ftt

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.nsu.fit.auth.domain.AuthInteractor
import ru.nsu.fit.common.ApiSettings
import ru.nsu.fit.common.FttRouter
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val authInteractor: AuthInteractor,
    private val apiSettings: ApiSettings,
    private val router: FttRouter
) : ViewModel() {


    fun reAuth() {
        viewModelScope.launch {
            while (true) {
                if (apiSettings.tokens.isRefreshTokenAvailable()) {
                    authInteractor.authRefresh()
                } else {
                    router.openAuthScreen()
                }
                delay(180_000)
            }
        }
    }
}