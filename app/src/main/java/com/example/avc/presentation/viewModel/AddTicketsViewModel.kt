package com.example.avc.presentation.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avc.database.entity.ProductEntity
import com.example.avc.database.entity.ProductPerTicketEntity
import com.example.avc.database.entity.TicketEntity
import com.example.avc.domain.model.TicketItem
import com.example.avc.domain.usecase.ProductPerTicketUseCase
import com.example.avc.domain.usecase.ProductUseCase
import com.example.avc.domain.usecase.TicketUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.Instant

class AddTicketsViewModel(
    private val productUseCase: ProductUseCase,
    private val ticketUseCase: TicketUseCase,
    private val productPerTicketUseCase: ProductPerTicketUseCase,
) : ViewModel() {

    private val currentState = AddTicketsState()

    private val _uiState: MutableStateFlow<AddTicketsState> by lazy { MutableStateFlow(currentState) }
    val uiState: StateFlow<AddTicketsState> get() = _uiState

    init {
        getProducts()
    }

    private fun getProducts() {
        viewModelScope.launch {
            productUseCase.getAllProducts().collect {
                it.map { product ->
                    _uiState.value.productsPerTicket =
                        _uiState.value.productsPerTicket.toMutableList().apply {
                            this.add(
                                TicketItem(
                                    productId = product.id,
                                    productName = product.name,
                                    image = product.image,
                                    amount = 0
                                )
                            )
                        }
                }
                _uiState.value.products = it
            }
        }
    }

    private fun onAddQuantity(product: TicketItem) {
        val newArray = _uiState.value.productsPerTicket.toMutableList().apply {
            val index = this.indexOfFirst {
                it.productId == product.productId
            }

            this[index] = this[index].copy(amount = this[index].amount + 1)
        }

        _uiState.tryEmit(
            _uiState.value.copy(
                productsPerTicket = newArray
            )
        )
    }

    private fun onRemoveQuantity(product: TicketItem) {
        val newArray = _uiState.value.productsPerTicket.toMutableList().apply {
            val index = this.indexOfFirst {
                it.productId == product.productId
            }

            if (this[index].amount > 0) {
                this[index] = this[index].copy(amount = this[index].amount - 1)
            }
        }

        _uiState.tryEmit(
            _uiState.value.copy(
                productsPerTicket = newArray
            )
        )
    }

    private fun onAddSelectedQuantity(quantity: Int) {
        val newArray = _uiState.value.productsPerTicket.toMutableList().apply {
            val index = this.indexOfFirst {
                it.productId == uiState.value.dialogProduct?.productId
            }

            this[index] = this[index].copy(amount = this[index].amount + quantity)
        }

        _uiState.tryEmit(
            _uiState.value.copy(
                productsPerTicket = newArray,
                showDialog = false,
                dialogProduct = null
            )
        )
    }

    private fun showDialogQuantity(product: TicketItem) {
        _uiState.tryEmit(
            _uiState.value.copy(
                showDialog = true,
                dialogProduct = product
            )
        )
    }

    private fun dismissDialogQuantity() {
        _uiState.tryEmit(
            _uiState.value.copy(
                showDialog = true,
                dialogProduct = null
            )
        )
    }

    private fun resetProducts() {
        _uiState.value.productsPerTicket.forEach {
            it.amount = 0
        }
    }

    private fun resetData() {
        resetProducts()
        _uiState.tryEmit(
            _uiState.value.copy(
                resetData = true,
                priceText = ""
            )
        )
    }

    private fun onPriceTextChanged(newText: String) {
        _uiState.tryEmit(
            _uiState.value.copy(
                priceText = newText
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

    private fun updateProductsStock(products: List<TicketItem>) {
        val updatedStockArray = _uiState.value.products.toMutableList().apply {
            products.forEach {
                val index = this.indexOfFirst { product ->
                    product.id == it.productId
                }

                this[index] = this[index].copy(amount = this[index].amount + products[index].amount)
            }
        }

        _uiState.tryEmit(
            _uiState.value.copy(
                products = updatedStockArray
            )
        )
    }

    private fun createNewTicket(price: Double, products: List<TicketItem>) = viewModelScope.launch {
        val newTicket = TicketEntity(id = 0, price = price, date = Instant.now().epochSecond)
        val ticketId = ticketUseCase.addNewTicket(newTicket)
        ticketId?.let { id ->
            productPerTicketUseCase.addProductToTickets(
                products.filter {
                    it.amount > 0
                }.map {
                    ProductPerTicketEntity(
                        amount = it.amount,
                        productId = it.productId,
                        ticketId = id
                    )
                }
            )
        } ?: Log.e("AddTicketViewModel", "Error insertando los productos al ticket")
        updateProductsStock(products = products)
        productUseCase.updateProducts(
            _uiState.value.products
        )
        resetData()
    }

    fun handleEvent(event: AddTicketsEvent) {
        when (event) {
            is AddTicketsEvent.OnAddQuantity -> onAddQuantity(event.product)
            is AddTicketsEvent.OnRemoveQuantity -> onRemoveQuantity(event.product)
            is AddTicketsEvent.OnAddSelectedQuantity -> onAddSelectedQuantity(event.quantity)
            is AddTicketsEvent.DismissDialog -> dismissDialogQuantity()
            is AddTicketsEvent.ShowDialog -> showDialogQuantity(event.product)
            is AddTicketsEvent.CreateNewTicket -> createNewTicket(event.price, event.products)
            is AddTicketsEvent.OnPriceTextChanged -> onPriceTextChanged(newText = event.text)
            is AddTicketsEvent.OnDialogTextChanged -> onDialogTextChanged(newText = event.text)
        }
    }

    sealed class AddTicketsEvent {
        class OnAddQuantity(val product: TicketItem) : AddTicketsEvent()
        class OnRemoveQuantity(val product: TicketItem) : AddTicketsEvent()
        class OnAddSelectedQuantity(val quantity: Int) : AddTicketsEvent()
        class ShowDialog(val product: TicketItem) : AddTicketsEvent()
        object DismissDialog : AddTicketsEvent()
        class CreateNewTicket(val price: Double, val products: List<TicketItem>) : AddTicketsEvent()
        class OnPriceTextChanged(val text: String) : AddTicketsEvent()
        class OnDialogTextChanged(val text: String) : AddTicketsEvent()
    }
}
data class AddTicketsState(
    var resetData: Boolean = false,
    var productsPerTicket: List<TicketItem> = emptyList(),
    var products: List<ProductEntity> = emptyList(),
    var showDialog: Boolean = false,
    var dialogProduct: TicketItem? = null,
    var priceText: String = "",
    var dialogText: String = ""
)
