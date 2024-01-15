package ru.kn_n.sevenwindscoffee.cafesModule.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kn_n.sevenwindscoffee.utils.constants.BASE_URL
import javax.inject.Inject
import javax.inject.Provider

class CafesRetrofitClient @Inject constructor() : Provider<CafesAPI> {
    override fun get(): CafesAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CafesAPI::class.java)
    }
}