package ru.kn_n.sevenwindscoffee.cafesModule.data

import ru.kn_n.sevenwindscoffee.cafesModule.domain.CafesRepository
import ru.kn_n.sevenwindscoffee.databaseModule.UserRepository
import ru.kn_n.sevenwindscoffee.utils.UserCache
import ru.kn_n.sevenwindscoffee.utils.base.NetworkResult
import ru.kn_n.sevenwindscoffee.utils.extensions.handleApi
import javax.inject.Inject

class CafesRepositoryImpl @Inject constructor(
    private val api: CafesAPI
) : CafesRepository {

    override suspend fun getCafes(token: String): NetworkResult<List<CafeResponse>> =
        handleApi { api.getCafes("Bearer $token") }

    override suspend fun getMenu(id: String, token: String): NetworkResult<List<MenuResponse>> =
        handleApi { api.getMenu( "Bearer $token", id) }
}