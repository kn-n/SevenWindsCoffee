package ru.kn_n.sevenwindscoffee.databaseModule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey
    val id: Int,
    val token: String,
    val email: String
)

@Entity(tableName = "order_table")
data class Order(
    @PrimaryKey
    val id: Int,
    val name: String,
    var count: Int,
    val price: Int
)