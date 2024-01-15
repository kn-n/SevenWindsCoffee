package ru.kn_n.sevenwindscoffee.authenticationModule.data

import ru.kn_n.sevenwindscoffee.authenticationModule.domain.AuthRepository
import ru.kn_n.sevenwindscoffee.utils.base.NetworkResult
import ru.kn_n.sevenwindscoffee.utils.extensions.handleApi
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthAPI
): AuthRepository {
    override suspend fun register(body: AuthBody): NetworkResult<AuthResponse> =
        handleApi { api.register(body) }

    override suspend fun login(body: AuthBody): NetworkResult<AuthResponse> =
        handleApi { api.login(body) }
}