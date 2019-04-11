package com.nuction.orderix.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface OrderDao {

    @Insert
    suspend fun insert(order: Order)

    @Update
    suspend fun update(order: Order)

    @Delete
    suspend fun delete(order: Order)

    @Query("DELETE FROM 'order'")
    suspend fun deleteAllOrders()

    @Query("SELECT * FROM 'order' ORDER BY id ASC")
    fun getAllOrders(): LiveData<List<Order>>
}