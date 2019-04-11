package com.nuction.orderix.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*

class OrderRepositoryImpl(private val orderDao: OrderDao) :
    OrderRepository {

    override fun insert(order: Order) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                orderDao.insert(order)
            }
        }
    }

    override fun update(order: Order) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                orderDao.update(order)
            }
        }
    }

    override fun delete(order: Order) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                orderDao.delete(order)
            }
        }
    }

    override fun deleteAllOrders() {
        runBlocking {
            this.launch(Dispatchers.IO) {
                orderDao.deleteAllOrders()
            }
        }
    }

    override fun getAllOrders(): LiveData<List<Order>> {
        return orderDao.getAllOrders()
    }

}