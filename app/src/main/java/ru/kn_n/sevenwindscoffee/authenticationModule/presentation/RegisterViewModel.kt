package ru.kn_n.sevenwindscoffee.authenticationModule.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.kn_n.sevenwindscoffee.authenticationModule.data.AuthBody
import ru.kn_n.sevenwindscoffee.authenticationModule.data.AuthRepositoryImpl
import ru.kn_n.sevenwindscoffee.authenticationModule.data.AuthResponse
import ru.kn_n.sevenwindscoffee.databaseModule.OrderRepository
import ru.kn_n.sevenwindscoffee.databaseModule.User
import ru.kn_n.sevenwindscoffee.databaseModule.UserRepository
import ru.kn_n.sevenwindscoffee.utils.base.NetworkResult
import ru.kn_n.sevenwindscoffee.utils.extensions.ZERO
import javax.inject.Inject

class RegisterViewModel @Inject constructor(
    private val repository: AuthRepositoryImpl,
    private val userRepository: UserRepository,
    private val orderRepository: OrderRepository
) : ViewModel() {

    val alreadyRegUser: LiveData<List<User>> = userRepository.readAllData

    private val _error = MutableLiveData<Pair<Int, String>>()
    val error: LiveData<Pair<Int, String>> = _error

    private val _exception = MutableLiveData<String>()
    val exception: LiveData<String> = _exception

    private val _success = MutableLiveData<String>()
    val success: LiveData<String> = _success

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun register(mail: String, password: String) {
        _loading.postValue(true)
        viewModelScope.launch {
            when (val data = repository.register(
                AuthBody(
                    login = mail,
                    password = password
                )
            )) {
                is NetworkResult.Success<AuthResponse> -> {
                    orderRepository.clear()
                    userRepository.addUser(
                        User(
                            id = Int.ZERO,
                            token = data.data.token,
                            email = mail
                        )
                    )
                    _success.postValue(data.data.token)
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

}