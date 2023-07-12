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