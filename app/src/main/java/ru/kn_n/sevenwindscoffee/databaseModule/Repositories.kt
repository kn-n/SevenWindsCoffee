package ru.kn_n.sevenwindscoffee.databaseModule

import androidx.lifecycle.LiveData
import javax.inject.Inject

class UserRepository @Inject constructor(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.getUser()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun update(user: User){
        userDao.updateUser(user)
    }

    suspend fun clear(){
        userDao.clear()
    }
}

class OrderRepository @Inject constructor(private val orderDao: OrderDao) {

    val readAllData: LiveData<List<Order>> = orderDao.getOrder()

    suspend fun addOrder(order: Order){
        orderDao.addOrder(order)
    }

    suspend fun updateOrder(order: Order){
        orderDao.updateOrder(order)
    }

    suspend fun deleteOrder(order: Order){
        orderDao.deleteOrder(order)
    }

    suspend fun clear(){
        orderDao.clear()
    }
}