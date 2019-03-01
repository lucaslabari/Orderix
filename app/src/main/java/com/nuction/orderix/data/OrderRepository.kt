package com.nuction.orderix.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Job

interface OrderRepository {

    fun insert(order: Order)
    fun update(order: Order)
    fun delete(order: Order)
    fun getAllOrders(): LiveData<List<Order>>
}