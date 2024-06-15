package com.canerture.booksapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.canerture.booksapp.data.model.BookBasket
import com.canerture.booksapp.data.model.Order
import com.canerture.booksapp.data.model.UserModel

@Database(entities = [BookBasket::class, UserModel::class, Order::class], version = 2)
abstract class BooksBasketDB : RoomDatabase() {

    abstract fun booksBasketDAOInterface(): BooksBasketDAO
}