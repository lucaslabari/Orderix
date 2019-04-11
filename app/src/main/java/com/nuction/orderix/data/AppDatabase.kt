package com.nuction.orderix.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nuction.orderix.data.OrderDao
import com.nuction.orderix.data.Order

@Database(entities = [Order::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

}