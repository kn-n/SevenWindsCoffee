package ru.kn_n.sevenwindscoffee.utils

import ru.kn_n.sevenwindscoffee.utils.extensions.EMPTY
import javax.inject.Inject

class UserCache @Inject constructor() {
    var token = String.EMPTY
    var email = String.EMPTY
}

class OrderCache @Inject constructor() {
    var order = mutableMapOf<Int, Coffee>()
}

data class Coffee(
    var id: Int,
    var name: String,
    var count: Int,
    var price: Int
)