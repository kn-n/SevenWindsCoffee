package ru.kn_n.sevenwindscoffee.cafesModule.presentation.cafes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kn_n.sevenwindscoffee.cafesModule.data.CafeResponse
import ru.kn_n.sevenwindscoffee.cafesModule.data.CafesRepositoryImpl
import ru.kn_n.sevenwindscoffee.databaseModule.OrderRepository
import ru.kn_n.sevenwindscoffee.databaseModule.User
import ru.kn_n.sevenwindscoffee.databaseModule.UserRepository
import ru.kn_n.sevenwindscoffee.utils.base.NetworkResult
import javax.inject.Inject

class CafesViewModel @Inject constructor(
    private val repository: CafesRepositoryImpl,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    val alreadyRegUser: LiveData<List<User>> = userRepository.readAllData

    private val _error = MutableLiveData<Pair<Int, String>>()
    val error: LiveData<Pair<Int, String>> = _error

    private val _exception = MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    private val _success = MutableLiveData<List<CafeResponse>>()
    val success: LiveData<List<CafeResponse>> = _success

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getCafes(token:String) {
        viewModelScope.launch {
            when (val data = repository.getCafes(token)) {
                is NetworkResult.Success<List<CafeResponse>> -> {
                    _success.postValue(data.data)
                    _loading.postValue(false)
                }
                is NetworkResult.Error<*> -> {
                    _error.postValue(Pair(data.code, data.message.orEmpty()))
                    _loading.postValue(false)
                }
                is NetworkResult.Exception<*> -> {
                    _exception.postValue("${data.e.message}")
                    _loading.postValue(false)
                }
            }
        }
    }

    fun clearOrder(){
        viewModelScope.launch {
            orderRepository.clear()
        }
    }
}