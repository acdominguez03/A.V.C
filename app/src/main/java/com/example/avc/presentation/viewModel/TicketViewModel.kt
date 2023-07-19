package com.example.avc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avc.database.entity.ProductEntity
import com.example.avc.database.entity.ProductPerTicketEntity
import com.example.avc.database.entity.TicketEntity
import com.example.avc.domain.usecase.ProductPerTicketUseCase
import com.example.avc.domain.usecase.ProductUseCase
import com.example.avc.domain.usecase.TicketUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketViewModel(
    private val productPerTicketUseCase: ProductPerTicketUseCase,
    private val ticketUseCase: TicketUseCase,
    private val productUseCase: ProductUseCase
) : ViewModel() {
    private val currentState = TicketState()

    private val _uiState: MutableStateFlow<TicketState> by lazy { MutableStateFlow(currentState) }
    val uiState: StateFlow<TicketState> get() = _uiState

    init {
        getAllTickets()
    }

    private fun getAllTickets() {
        viewModelScope.launch {
            ticketUseCase.getAllTickets().collect { tickets ->
                productPerTicketUseCase.getAllProductsPerTickets().collect { productsPerTicket ->
                    productUseCase.getAllProducts().collect { products ->
                        _uiState.tryEmit(
                            _uiState.value.copy(
                                tickets = tickets,
                                productsPerTicket = productsPerTicket,
                                products = products
                            )
                        )
                    }
                }
            }
        }
    }

    fun getProductsForOneTicket(ticketId: Long): List<ProductPerTicketEntity> {
        return _uiState.value.productsPerTicket.filter {
            it.ticketId == ticketId
        }
    }

    fun getNameOfTheProduct(productId: Long): String {
        return _uiState.value.products.firstOrNull {
            it.id == productId
        }?.name ?: ""
    }
}

data class TicketState(
    var tickets: List<TicketEntity> = emptyList(),
    var productsPerTicket: List<ProductPerTicketEntity> = emptyList(),
    var products: List<ProductEntity> = emptyList()
)