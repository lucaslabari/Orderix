package com.nuction.orderix.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.nuction.orderix.data.Order
import com.nuction.orderix.data.OrderRepository


class OrderViewModel(private val repository: OrderRepository) : ViewModel() {

    fun insert(order: Order) {
        repository.insert(order)
    }

    fun update(order: Order) {
        repository.update(order)
    }

    fun delete(order: Order) {
        repository.delete(order)
    }

    fun getAllOrders(): LiveData<List<Order>> {
        return repository.getAllOrders()
    }
}