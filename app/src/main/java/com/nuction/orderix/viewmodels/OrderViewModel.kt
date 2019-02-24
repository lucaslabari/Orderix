package com.nuction.orderix.viewmodels

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import android.app.Application
import com.nuction.orderix.data.Order
import com.nuction.orderix.data.OrderRepository


class OrderViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = OrderRepository(application)
    private val allOrders: LiveData<List<Order>> = repository.getAllOrders()

    fun insert(order: Order) {
        repository.insert(order)
    }

    fun update(order: Order) {
        repository.update(order)
    }

    fun delete(order: Order) {
        repository.delete(order)
    }

    fun deleteAllOrders() {
        repository.deleteAllOrders()
    }

    fun getAllOrders(): LiveData<List<Order>> {
        return allOrders
    }
}