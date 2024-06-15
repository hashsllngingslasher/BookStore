package com.canerture.booksapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//@Entity(
//    tableName = "orders",
//    foreignKeys = [
//        ForeignKey(entity = UserModel::class, parentColumns = ["userId"], childColumns = ["userId"]),
//        ForeignKey(entity = BookBasket::class, parentColumns = ["id"], childColumns = ["bookId"])
//    ]
//)
@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId")
    val orderId: Int = 0,

    @ColumnInfo(name = "userId")
    val userId: Int,

    @ColumnInfo(name = "bookId")
    val bookId: Int,

    @ColumnInfo(name = "bookName")
    val name: String?,

    @ColumnInfo(name = "price")
    val price: String?,

    @ColumnInfo(name = "quantity")
    val quantity: Int,

    @ColumnInfo(name = "orderDate")
    val orderDate: Long
)

