package ru.kn_n.sevenwindscoffee.authenticationModule.domain

import ru.kn_n.sevenwindscoffee.authenticationModule.data.AuthBody
import ru.kn_n.sevenwindscoffee.authenticationModule.data.AuthResponse
import ru.kn_n.sevenwindscoffee.utils.base.NetworkResult

interface AuthRepository {
    suspend fun register(body: AuthBody) : NetworkResult<AuthResponse>
    suspend fun login(body: AuthBody) : NetworkResult<AuthResponse>
}