package com.example.avc.di

import androidx.room.Room
import com.example.avc.data.ProductRepositoryImpl
import com.example.avc.database.AVCDatabase
import com.example.avc.domain.repositories.ProductRepository
import com.example.avc.presentation.viewModel.AddTicketsViewModel
import com.example.avc.presentation.viewModel.DeliveryViewModel
import com.example.avc.presentation.viewModel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val di = module {

    single {
        Room.databaseBuilder(
            this.androidContext(),
            AVCDatabase::class.java,
            "avc_database"
        ).build()
    }

    single {
        val database = get<AVCDatabase>()
        database.productDao()
    }

    factory<ProductRepository> {
        ProductRepositoryImpl(get())
    }

    viewModelOf(::HomeViewModel)
    viewModelOf(::AddTicketsViewModel)
    viewModelOf(::DeliveryViewModel)
}
