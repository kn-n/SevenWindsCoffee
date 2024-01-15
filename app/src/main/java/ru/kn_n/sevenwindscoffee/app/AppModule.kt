package ru.kn_n.sevenwindscoffee.app

import android.content.Context
import ru.kn_n.sevenwindscoffee.authenticationModule.data.AuthAPI
import ru.kn_n.sevenwindscoffee.authenticationModule.data.AuthRetrofitClient
import ru.kn_n.sevenwindscoffee.cafesModule.data.CafesAPI
import ru.kn_n.sevenwindscoffee.cafesModule.data.CafesRetrofitClient
import ru.kn_n.sevenwindscoffee.databaseModule.AppDatabase
import ru.kn_n.sevenwindscoffee.databaseModule.OrderRepository
import ru.kn_n.sevenwindscoffee.databaseModule.UserRepository
import ru.kn_n.sevenwindscoffee.utils.OrderCache
import ru.kn_n.sevenwindscoffee.utils.UserCache
import toothpick.ktp.binding.module

fun appModule(context: Context) = module {
    bind(Context::class.java).toInstance(context)

    bind(AuthAPI::class.java).toProvider(AuthRetrofitClient::class.java)
    bind(CafesAPI::class.java).toProvider(CafesRetrofitClient::class.java)

    val userDao = AppDatabase.getDatabase(
        context
    ).userDao()
    bind(UserRepository::class.java).toInstance(UserRepository(userDao))

    val orderDao = AppDatabase.getDatabase(
        context
    ).orderDao()
    bind(OrderRepository::class.java).toInstance(OrderRepository(orderDao))

}