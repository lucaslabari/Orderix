package com.nuction.orderix.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Order::class], version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

}