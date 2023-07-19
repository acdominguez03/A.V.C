package com.example.avc.presentation.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avc.database.entity.DeliveryEntity
import com.example.avc.database.entity.ProductEntity
import com.example.avc.database.entity.UserEntity
import com.example.avc.domain.usecase.AddDeliveryUseCase
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DeliveryViewModel(
    private val addDeliveryUseCase: AddDeliveryUseCase
) : ViewModel(), KoinComponent {

    var state by mutableStateOf(
        DeliveryState(
            products = listOf(),
            users = listOf()
        )
    )

    init {
        viewModelScope.launch {
            addDeliveryUseCase.getAllProducts().collect {
                addDeliveryUseCase.getAllUsers().collect { users ->
                    state = state.copy(
                        products = it,
                        users = users
                    )
                }
            }
        }
    }
    fun addAllUsers() = viewModelScope.launch {
        addDeliveryUseCase.insertAllUsers()
    }

    fun addDelivery(deliveryEntity: DeliveryEntity) = viewModelScope.launch {
        addDeliveryUseCase.insertDelivery(deliveryEntity)
    }

    /*fun createDeliveryEntity(user: UserEntity, price: Double): DeliveryEntity {
        return DeliveryEntity(userId = user.id, price = price, date = Instant.now().epochSecond)
    }*/

    fun handleEvent(event: DeliveryEvent) {
        when (event) {
            is DeliveryEvent.OnPersonSelected -> TODO()
        }
    }

    sealed class DeliveryEvent {
        class OnPersonSelected(val user: UserEntity, val price: Double): DeliveryEvent()
    }
}

data class DeliveryState(
    var products: List<ProductEntity>,
    var users: List<UserEntity>
)