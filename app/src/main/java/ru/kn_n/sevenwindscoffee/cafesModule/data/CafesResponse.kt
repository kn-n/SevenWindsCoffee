package ru.kn_n.sevenwindscoffee.cafesModule.data

import java.io.Serializable

data class Cafes(
    val cafes: List<CafeResponse>
) : Serializable

data class CafeResponse(
    val id: Int,
    val name: String,
    val point: Point,
) : Serializable

data class Point(
    val latitude: String,
    val longitude: String,
) : Serializable

data class MenuResponse(
    val id: Int,
    val name: String,
    val imageURL: String,
    val price: Int
)