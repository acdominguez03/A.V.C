package com.example.avc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avc.composables.CustomCheckbox
import com.example.avc.database.entity.DeliveryEntity
import com.example.avc.database.entity.DeliveryStatus
import com.example.avc.database.entity.UserEntity
import com.example.avc.domain.model.DeliveryItemModel
import com.example.avc.domain.usecase.DeliveryUseCase
import com.example.avc.domain.usecase.ProductUseCase
import com.example.avc.domain.usecase.UserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class DeliveryViewModel(
    private val productUseCase: ProductUseCase,
    private val userUseCase: UserUseCase,
    private val deliveryUseCase: DeliveryUseCase
) : ViewModel(), KoinComponent {

    private val _uiState: MutableStateFlow<DeliveryState> by lazy { MutableStateFlow(DeliveryState()) }
    val uiState: StateFlow<DeliveryState> get() = _uiState

    init {
        viewModelScope.launch {
            productUseCase.getAllProducts().collect {
                userUseCase.getAllUsers().collect { users ->
                    _uiState.tryEmit(
                        _uiState.value.copy(
                            items = it.map {
                                DeliveryItemModel(
                                    product = it,
                                    quantity = 1,
                                    checkBoxStatus = DeliveryStatus.NOTHING,
                                    expandDropDown = false
                                )
                            },
                            users = users
                        )
                    )
                }
            }
        }
    }
    fun addAllUsers() = viewModelScope.launch {
        userUseCase.insertAllUsers()
    }

    private fun addDeliveries() = viewModelScope.launch {
        _uiState.value.userSelected?.let { user ->
            val deliveries = _uiState.value.deliveries.toMutableList().apply {
                _uiState.value.items.forEach { item ->
                    if (item.checkBoxStatus != DeliveryStatus.NOTHING) {
                        for (i in 1..item.quantity) {
                            this.add(
                                DeliveryEntity(
                                    id = 0,
                                    status = item.checkBoxStatus,
                                    productId = item.product.id,
                                    userId = user.id
                                )
                            )
                        }
                    }
                }
            }

            _uiState.tryEmit(
                _uiState.value.copy(
                    deliveries = deliveries
                )
            )

            deliveryUseCase.insertDeliveries(deliveries = _uiState.value.deliveries)

            resetData()
        }
    }

    private fun resetData() {
        _uiState.tryEmit(
            _uiState.value.copy(
                items = _uiState.value.items.apply {
                    this.forEach {
                        it.quantity = 1
                        it.expandDropDown = false
                        it.checkBoxStatus = DeliveryStatus.NOTHING
                    }
                },
                userText = "",
                userSelected = null
            )
        )
    }

    private fun onUserSelected(user: UserEntity) {
        _uiState.tryEmit(
            _uiState.value.copy(
                userSelected = user
            )
        )
    }

    fun filterItems(items: List<UserEntity>): List<UserEntity> {
        return items.filter {
            it.name.lowercase()
                .contains(_uiState.value.userText.lowercase())
        }
    }

    private fun onDismissQuantityDropDown(item: DeliveryItemModel) {
        val newArray = _uiState.value.items.toMutableList().apply {
            val index = this.indexOfFirst {
                it == item
            }

            this[index] = this[index].copy(
                expandDropDown = false
            )
        }

        _uiState.tryEmit(
            _uiState.value.copy(
                items = newArray
            )
        )
    }

    private fun onQuantityDropDownTouched(item: DeliveryItemModel) {
        val newArray = _uiState.value.items.toMutableList().apply {
            val index = this.indexOfFirst {
                it == item
            }

            this[index] = this[index].copy(
                expandDropDown = !this[index].expandDropDown
            )
        }

        _uiState.tryEmit(
            _uiState.value.copy(
                items = newArray
            )
        )
    }

    private fun onQuantitySelected(item: DeliveryItemModel, quantity: Int) {
        val newArray = _uiState.value.items.toMutableList().apply {
            val index = this.indexOfFirst {
                it == item
            }

            this[index] = this[index].copy(
                expandDropDown = false,
                quantity = quantity
            )
        }

        _uiState.tryEmit(
            _uiState.value.copy(
                items = newArray
            )
        )
    }

    private fun onCheckBoxSelected(item: DeliveryItemModel, deliveryStatus: DeliveryStatus) {
        val newArray = _uiState.value.items.toMutableList().apply {
            val index = this.indexOfFirst {
                it == item
            }

            this[index] = this[index].copy(
                checkBoxStatus = deliveryStatus
            )
        }

        _uiState.tryEmit(
            _uiState.value.copy(
                items = newArray
            )
        )
    }

    fun handleEvent(event: DeliveryEvent) {
        when (event) {
            is DeliveryEvent.OnUserSelected -> onUserSelected(event.user)
            is DeliveryEvent.OnAddButtonSelected -> addDeliveries()
            is DeliveryEvent.OnUserTextChanged -> {
                _uiState.tryEmit(
                    _uiState.value.copy(
                        userText = event.newText,
                        expandUserDropDown = true
                    )
                )
            }
            is DeliveryEvent.OnDismissDropDown -> {
                _uiState.tryEmit(
                    _uiState.value.copy(
                        userText = event.newText,
                        userSelected = event.user,
                        expandUserDropDown = false
                    )
                )
            }
            is DeliveryEvent.OnQuantityDropDownTouched -> onQuantityDropDownTouched(event.item)
            is DeliveryEvent.OnDismissQuantityDropDown -> onDismissQuantityDropDown(event.item)
            is DeliveryEvent.OnQuantitySelected -> onQuantitySelected(event.item, event.quantity.toInt())
            is DeliveryEvent.OnCheckBoxSelected -> onCheckBoxSelected(event.item, event.deliveryStatus)
        }
    }

    sealed class DeliveryEvent {
        class OnUserSelected(val user: UserEntity) : DeliveryEvent()
        object OnAddButtonSelected : DeliveryEvent()
        class OnUserTextChanged(val newText: String) : DeliveryEvent()
        class OnDismissDropDown(val newText: String, val user: UserEntity?) : DeliveryEvent()
        class OnQuantityDropDownTouched(val item: DeliveryItemModel) : DeliveryEvent()
        class OnDismissQuantityDropDown(val item: DeliveryItemModel) : DeliveryEvent()
        class OnQuantitySelected(val quantity: String, val item: DeliveryItemModel) : DeliveryEvent()
        class OnCheckBoxSelected(val deliveryStatus: DeliveryStatus, val item: DeliveryItemModel) : DeliveryEvent()
    }
}

data class DeliveryState(
    var items: List<DeliveryItemModel> = emptyList(),
    var users: List<UserEntity> = emptyList(),
    var deliveries: List<DeliveryEntity> = emptyList(),
    var userSelected: UserEntity? = null,
    var userText: String = "",
    var expandUserDropDown: Boolean = false,
    var checkBoxOptions: List<DeliveryStatus> = listOf(
        DeliveryStatus.PAID,
        DeliveryStatus.NO_PAID,
        DeliveryStatus.INVITATION
    )
)
