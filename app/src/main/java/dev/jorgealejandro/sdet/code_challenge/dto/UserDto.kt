package dev.jorgealejandro.sdet.code_challenge.dto

import dev.jorgealejandro.sdet.code_challenge.utils.EMPTY_FIELD

data class UserDto(
    val email: String = EMPTY_FIELD,
    val password: String = EMPTY_FIELD,
)