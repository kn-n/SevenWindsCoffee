package ru.kn_n.sevenwindscoffee.cafesModule.presentation.order

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kn_n.sevenwindscoffee.databaseModule.Order
import ru.kn_n.sevenwindscoffee.databaseModule.OrderRepository
import javax.inject.Inject

class OrderViewModel @Inject constructor(
    private val orderRepository: OrderRepository
) : ViewModel() {
    val order: LiveData<List<Order>> = orderRepository.readAllData

    fun update(order: Order){
        viewModelScope.launch {
            orderRepository.updateOrder(order)
        }
    }

    fun delete(order: Order){
        viewModelScope.launch {
            orderRepository.deleteOrder(order)
        }
    }
}