package com.example.avc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avc.database.entity.DeliveryEntity
import com.example.avc.database.entity.DeliveryStatus
import com.example.avc.database.entity.ProductEntity
import com.example.avc.database.entity.UserEntity
import com.example.avc.domain.model.ExpensesItemModel
import com.example.avc.domain.usecase.DeliveryUseCase
import com.example.avc.domain.usecase.ProductUseCase
import com.example.avc.domain.usecase.UserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExpensesViewModel(
    private val userUseCase: UserUseCase,
    private val deliveryUseCase: DeliveryUseCase,
    private val productUseCase: ProductUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<ExpensesState> by lazy { MutableStateFlow(ExpensesState()) }
    val uiState: StateFlow<ExpensesState> get() = _uiState

    init {
        viewModelScope.launch {
            userUseCase.getUsersWithExpenses().collect { users ->
                deliveryUseCase.getAllDeliveries().collect { deliveries ->
                    productUseCase.getAllProducts().collect { products ->
                        _uiState.tryEmit(
                            _uiState.value.copy(
                                usersWithExpenses = users,
                                deliveries = deliveries,
                                products = products
                            )
                        )
                    }
                }
            }
        }
    }

    fun getUserExpenses(userId: Long): MutableList<ExpensesItemModel> {
        val array: MutableList<ExpensesItemModel> = mutableListOf()
        var quantity = 1
        _uiState.value.deliveries.filter {
            it.userId == userId && it.status == DeliveryStatus.NO_PAID
        }.forEach { delivery ->
            val newItem = ExpensesItemModel(
                productPrice = _uiState.value.products[delivery.productId.toInt()].price,
                productName = _uiState.value.products[delivery.productId.toInt()].name,
                productId = delivery.productId,
                quantity = quantity
            )

            if (array.indexOf(newItem) >= 0) {
                quantity += 1
            } else {
                array.add(newItem)
            }
        }
        return array
    }

    private fun showExpensesDialog() {
        _uiState.tryEmit(
            _uiState.value.copy(
                showExpensesDialog = true
            )
        )
    }

    private fun onCancelExpensesDialog() {
        _uiState.tryEmit(
            _uiState.value.copy(
                showExpensesDialog = false
            )
        )
    }

    private fun onConfirmExpensesDialog(quantity: String, item: ExpensesItemModel) {
        _uiState.tryEmit(
            _uiState.value.copy(
                expenses = _uiState.value.expenses.toMutableList().apply {
                    val index = this.indexOfFirst {
                        it == item
                    }

                    this[index] = this[index].copy(quantity = this[index].quantity - quantity.toInt())
                },
                showExpensesDialog = false
            )
        )
    }

    private fun onDialogTextChanged(newText: String) {
        _uiState.tryEmit(
            _uiState.value.copy(
                dialogText = newText
            )
        )
    }

    fun handleEvent(event: ExpensesEvent) {
        when (event) {
            is ExpensesEvent.ShowExpensesDialog -> showExpensesDialog()
            is ExpensesEvent.OnConfirmExpensesDialog -> onConfirmExpensesDialog(quantity = event.quantity, item = event.item)
            is ExpensesEvent.OnCancelExpensesDialog -> onCancelExpensesDialog()
            is ExpensesEvent.OnDialogTextChanged -> onDialogTextChanged(newText = event.newText)
            is ExpensesEvent.OnExpandExpensesListTouched -> {
                _uiState.tryEmit(
                    _uiState.value.copy(
                        expandExpensesList = !_uiState.value.expandExpensesList
                    )
                )
            }
        }
    }

    sealed class ExpensesEvent {
        object ShowExpensesDialog : ExpensesEvent()
        class OnConfirmExpensesDialog(val quantity: String, val item: ExpensesItemModel) : ExpensesEvent()
        object OnCancelExpensesDialog : ExpensesEvent()
        class OnDialogTextChanged(val newText: String) : ExpensesEvent()
        object OnExpandExpensesListTouched : ExpensesEvent()
    }
}

data class ExpensesState(
    val expenses: List<ExpensesItemModel> = emptyList(),
    val usersWithExpenses: List<UserEntity> = emptyList(),
    val deliveries: List<DeliveryEntity> = emptyList(),
    val products: List<ProductEntity> = emptyList(),
    val dialogText: String = "",
    val showExpensesDialog: Boolean = false,
    val expandExpensesList: Boolean = false
)
