package com.nuction.orderix.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

/**
 * @author Lucas Labari
 * @since 22/02/19
 */
@Parcelize()
@Entity()
data class Order (@PrimaryKey(autoGenerate = true) val id: Long?,
                  @ColumnInfo(name = "client_name") val clientName: String,
                  @ColumnInfo(name = "sales_condition") val salesCondition: Int,
                  @ColumnInfo(name = "comments") val comments: String?,
                  @ColumnInfo(name = "total") val total: Float) : Parcelable







