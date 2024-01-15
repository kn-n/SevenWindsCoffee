package ru.kn_n.sevenwindscoffee.authenticationModule.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kn_n.sevenwindscoffee.utils.constants.BASE_URL
import javax.inject.Inject
import javax.inject.Provider

class AuthRetrofitClient @Inject constructor() : Provider<AuthAPI> {
    override fun get(): AuthAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthAPI::class.java)
    }
}