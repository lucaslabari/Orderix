package com.nuction.orderix.data

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class OrderRepository(application: Application) {

    private val orderDao: OrderDao
    private val allOrders: LiveData<List<Order>>

    init {
        val database = AppDatabase.getInstance(application.applicationContext)
        orderDao = database!!.orderDao()
        allOrders = orderDao.getAllOrders()
    }

    fun insert(order: Order) = runBlocking {
        this.launch(Dispatchers.IO) {
            orderDao.insert(order)
        }
    }

    fun update(order: Order) = runBlocking {
        this.launch(Dispatchers.IO) {
            orderDao.update(order)
        }
    }

    fun delete(order: Order) = runBlocking {
        this.launch(Dispatchers.IO) {
            orderDao.delete(order)
        }
    }

    fun deleteAllOrders() = runBlocking {
        this.launch(Dispatchers.IO) {
            orderDao.deleteAllOrders()
        }
    }

    fun getAllOrders(): LiveData<List<Order>> {
        return allOrders
    }

}