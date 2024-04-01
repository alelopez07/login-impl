package dev.jorgealejandro.sdet.code_challenge.ui.states

import dev.jorgealejandro.sdet.code_challenge.dto.UserDto

data class LoginState(
    val userDto: UserDto? = null,
    val isLoading: Boolean = false,
    val isLoginSuccess: Boolean = false,
    val isError: Boolean = false
)