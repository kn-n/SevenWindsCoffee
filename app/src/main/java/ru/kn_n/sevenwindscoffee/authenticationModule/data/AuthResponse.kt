package ru.kn_n.sevenwindscoffee.authenticationModule.data

data class AuthResponse (
    val token: String,
    val tokenLifetime: Int
)