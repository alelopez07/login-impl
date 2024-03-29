package dev.jorgealejandro.sdet.code_challenge.ui.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.jorgealejandro.sdet.code_challenge.dto.UserDto
import dev.jorgealejandro.sdet.code_challenge.ui.base.BaseViewModel
import dev.jorgealejandro.sdet.code_challenge.ui.base.UIEvent
import dev.jorgealejandro.sdet.code_challenge.ui.events.LoginEvent
import dev.jorgealejandro.sdet.code_challenge.ui.states.LoginState
import dev.jorgealejandro.sdet.code_challenge.utils.TIME_DELAY
import dev.jorgealejandro.sdet.code_challenge.utils.launch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

typealias User = String
typealias Password = String

class LoginViewModel: BaseViewModel() {
    var uiState by mutableStateOf(LoginState())

    init {
        uiState = uiState.copy(isLoading = true)
    }

    override fun onUIEvent(event: UIEvent) {
        when (event) {
            is LoginEvent.DoLogin -> processingCredentials(event.user)
        }
    }

    private fun processingCredentials(user: UserDto?) {
        if (user == null) return

        val defaultCredentials: Pair<User, Password> = Pair(
            "jorgealopez.rivas@gmail.com",
            "123456"
        )

        launch {
            completeTask()
        }

        uiState = if (user.email === defaultCredentials.first &&
            user.password === defaultCredentials.second) {
            uiState.copy(
                isSuccess = true,
                userDto = user,
                isLoading = false
            )
        } else {
            uiState.copy(
                isSuccess = false,
                isError = true,
                isLoading = false
            )
        }
    }

    private suspend fun completeTask() {
        delay(TIME_DELAY)
    }
}
