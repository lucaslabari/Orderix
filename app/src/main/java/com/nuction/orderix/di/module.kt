package com.nuction.orderix.di

import androidx.room.Room
import com.nuction.orderix.data.AppDatabase
import com.nuction.orderix.data.OrderRepository
import com.nuction.orderix.data.OrderRepositoryImpl
import com.nuction.orderix.viewmodels.OrderViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Room database
    single {
        synchronized(AppDatabase::class) {
            Room.databaseBuilder(androidApplication(), AppDatabase::class.java, "orderix.db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    single { get<AppDatabase>().orderDao() }

    // OrderRepository
    //factory<OrderRepository> { OrderRepositoryImpl(get()) }

    // single instance of OrderRepository
    single<OrderRepository> { OrderRepositoryImpl(get()) }

    // OrderViewModel ViewModel
    viewModel { OrderViewModel(get()) }
}