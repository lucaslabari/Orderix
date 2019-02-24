package com.nuction.orderix.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderDao {

    @Insert
    fun insert(order: Order)

    @Update
    fun update(order: Order)

    @Delete
    fun delete(order: Order)

    @Query("DELETE FROM `order`")
    fun deleteAllOrders()

    @Query("SELECT * FROM `order` ORDER BY id ASC")
    fun getAllOrders(): LiveData<List<Order>>
}