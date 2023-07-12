package com.example.avc.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.avc.domain.repositories.ProductRepository
import org.koin.core.component.KoinComponent

class DeliveryViewModel(
    repository: ProductRepository
) : ViewModel(), KoinComponent {
    var allProducts = repository.getAllProducts()
}