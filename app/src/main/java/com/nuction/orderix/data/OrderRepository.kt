package com.nuction.orderix.data

import androidx.lifecycle.LiveData

interface OrderRepository {

    fun insert(order: Order)
    fun update(order: Order)
    fun delete(order: Order)
    fun deleteAllOrders()
    fun getAllOrders(): LiveData<List<Order>>
}