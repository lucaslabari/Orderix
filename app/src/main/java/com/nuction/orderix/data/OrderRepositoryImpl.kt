package com.nuction.orderix.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO

class OrderRepositoryImpl(private val orderDao: OrderDao) :  OrderRepository {

    override fun insert(order: Order) {
        // IO, Main, Default
        CoroutineScope(IO).launch{
                orderDao.insert(order)
        }
    }

    override fun update(order: Order) {

        CoroutineScope(IO).launch {
            orderDao.update(order)
        }

    }

    override fun delete(order: Order) {
        CoroutineScope(IO).launch{
                orderDao.delete(order)
        }
    }

//    override fun deleteAllOrders() {
//        runBlocking {
//            this.launch(Dispatchers.IO) {
//                orderDao.deleteAllOrders()
//            }
//        }
//    }

    override fun getAllOrders(): LiveData<List<Order>> {
        return orderDao.getAllOrders()
    }

}