package com.nuction.orderix.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nuction.orderix.data.Order
import com.nuction.orderix.data.OrderRepository


class OrderViewModel(private val repository: OrderRepository) : ViewModel() {

    private val myOrders: LiveData<List<Order>>

    init {
        myOrders = repository.getAllOrders()
    }


    fun insert(order: Order) {
        repository.insert(order)
    }

    fun update(order: Order) {
        repository.update(order)
    }

    fun delete(order: Order) {
        repository.delete(order)
    }

//    fun deleteAllOrders() {
//        repository.deleteAllOrders()
//    }

    fun getAllOrders(): LiveData<List<Order>> {
        return myOrders
    }
}