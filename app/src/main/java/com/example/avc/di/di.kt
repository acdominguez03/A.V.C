package com.example.avc.di

import androidx.room.Room
import com.example.avc.data.*
import com.example.avc.database.AVCDatabase
import com.example.avc.domain.repositories.*
import com.example.avc.domain.usecase.*
import com.example.avc.presentation.viewModel.AddTicketsViewModel
import com.example.avc.presentation.viewModel.DeliveryViewModel
import com.example.avc.presentation.viewModel.HomeViewModel
import com.example.avc.presentation.viewModel.TicketViewModel
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

    // DAO
    single {
        val database = get<AVCDatabase>()
        database.productDao()
    }
    single {
        val database = get<AVCDatabase>()
        database.ticketDao()
    }
    single {
        val database = get<AVCDatabase>()
        database.deliveryDao()
    }
    single {
        val database = get<AVCDatabase>()
        database.userDao()
    }
    single {
        val database = get<AVCDatabase>()
        database.productPerTicketDao()
    }

    // Repositories
    factory<ProductRepository> {
        ProductRepositoryImpl(get())
    }

    factory<TicketRepository> {
        TicketRepositoryImpl(get())
    }

    factory<ProductPerTicketRepository> {
        ProductPerTicketRepositoryImpl(get())
    }

    factory<UserRepository> {
        UserRepositoryImpl(get())
    }

    factory<DeliveryRepository> {
        DeliveryRepositoryImpl(get())
    }

    // View Models
    viewModelOf(::HomeViewModel)
    viewModelOf(::AddTicketsViewModel)
    viewModelOf(::DeliveryViewModel)
    viewModelOf(::TicketViewModel)

    // Use Cases
    factory { AddTicketsUseCase() }
    factory { AddDeliveryUseCase() }
    factory { ProductPerTicketUseCase() }
    factory { TicketUseCase() }
    factory { ProductUseCase() }
}
