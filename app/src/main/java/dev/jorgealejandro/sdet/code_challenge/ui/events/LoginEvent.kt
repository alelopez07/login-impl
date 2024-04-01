package dev.jorgealejandro.sdet.code_challenge.ui.events

import dev.jorgealejandro.sdet.code_challenge.dto.UserDto
import dev.jorgealejandro.sdet.code_challenge.ui.base.UIEvent

sealed class LoginEvent: UIEvent {
    data class DoLogin(val user: UserDto? = null): LoginEvent()
    data object DoLogout: LoginEvent()
    data object OnLoadingDispatched: LoginEvent()
    data object OnSuccessDispatched: LoginEvent()
    data object OnErrorDispatched: LoginEvent()
}