package com.example.avc.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avc.database.entity.ProductEntity
import com.example.avc.domain.repositories.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    var allProducts = repository.getAllProducts()

    fun insertProducts() = viewModelScope.launch {
        insertAll(
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

    fun insert(productEntity: ProductEntity) = viewModelScope.launch {
        repository.insertProduct(productEntity)
    }

    fun insertAll(products: List<ProductEntity>) = viewModelScope.launch {
        repository.insertAll(products)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAllProducts()
    }
}
