package com.example.avc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avc.composables.custom_tab_bar.BottomBarScreen
import com.example.avc.database.entity.ProductEntity
import com.example.avc.domain.usecase.ProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val useCase: ProductUseCase
) : ViewModel() {

    private val _uiState: MutableStateFlow<HomeState> by lazy { MutableStateFlow(HomeState()) }
    val uiState: StateFlow<HomeState> get() = _uiState

    init {
        viewModelScope.launch {
            useCase.getAllProducts().collect {
                _uiState.tryEmit(
                    _uiState.value.copy(
                        products = it
                    )
                )
            }
        }
    }

    fun insertProducts() = viewModelScope.launch {
        useCase.insertAll(
            listOf(
                ProductEntity(id = 1, name = "Coca-Cola", price = 1.0, image = "R.drawable.ic_cocacola", 0),
                ProductEntity(id = 2, name = "Coca-Cola Zero", price = 1.0, image = "R.drawable.ic_cocacola", 0),
                ProductEntity(id = 3, name = "Nestea", price = 1.0, image = "R.drawable.ic_nestea", 0),
                ProductEntity(id = 4, name = "Aquarius", price = 1.0, image = "R.drawable.ic_aquarius", 0),
                ProductEntity(id = 5, name = "Cerveza", price = 1.0, image = "R.drawable.ic_beer", 0),
                ProductEntity(id = 6, name = "Cerveza 0%", price = 1.0, image = "R.drawable.ic_beer0", 0),
                ProductEntity(id = 7, name = "Fanta", price = 1.0, image = "R.drawable.ic_fanta", 0),
                ProductEntity(id = 8, name = "Helado", price = 1.0, image = "R.drawable.ic_icecream", 0)
            )
        )
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        useCase.deleteAll()
    }
}

data class HomeState(
    var products: List<ProductEntity> = emptyList()
)
