package ru.kn_n.sevenwindscoffee.databaseModule

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("DELETE FROM user_table")
    suspend fun clear()

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM user_table")
    fun getUser(): LiveData<List<User>>
}

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addOrder(order: Order)

    @Update
    suspend fun updateOrder(order: Order)

    @Delete
    suspend fun deleteOrder(order: Order)


    @Query("DELETE FROM order_table")
    suspend fun clear()

    @Query("SELECT * FROM order_table")
    fun getOrder(): LiveData<List<Order>>
}