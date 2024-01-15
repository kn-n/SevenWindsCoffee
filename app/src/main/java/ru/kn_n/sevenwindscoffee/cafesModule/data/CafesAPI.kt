package ru.kn_n.sevenwindscoffee.cafesModule.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import ru.kn_n.sevenwindscoffee.utils.constants.LOCATIONS_URL
import ru.kn_n.sevenwindscoffee.utils.constants.MENU_URL

interface CafesAPI {
    @GET(LOCATIONS_URL)
    suspend fun getCafes(
        @Header("Authorization") auth: String
    ) : Response<List<CafeResponse>>

    @GET(MENU_URL)
    suspend fun getMenu(
        @Header("Authorization") auth: String,
        @Path("id") id: String
    ) : Response<List<MenuResponse>>
}