package ru.kn_n.sevenwindscoffee.cafesModule.domain

import ru.kn_n.sevenwindscoffee.cafesModule.data.CafeResponse
import ru.kn_n.sevenwindscoffee.cafesModule.data.MenuResponse
import ru.kn_n.sevenwindscoffee.utils.base.NetworkResult

interface CafesRepository {
    suspend fun getCafes(token: String): NetworkResult<List<CafeResponse>>
    suspend fun getMenu(id: String, token: String): NetworkResult<List<MenuResponse>>
}