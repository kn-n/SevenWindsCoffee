package ru.kn_n.sevenwindscoffee.authenticationModule.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.kn_n.sevenwindscoffee.utils.constants.LOGIN_URL
import ru.kn_n.sevenwindscoffee.utils.constants.REGISTER_URL

interface AuthAPI {
    @POST(REGISTER_URL)
    suspend fun register(
        @Body body: AuthBody
    ) : Response<AuthResponse>

    @POST(LOGIN_URL)
    suspend fun login(
        @Body body: AuthBody
    ) : Response<AuthResponse>
}