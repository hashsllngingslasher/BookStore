package com.canerture.booksapp.data.room

import androidx.room.Dao
import androidx.room.Embedded
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Relation
import androidx.room.Transaction
import com.canerture.booksapp.data.model.BookBasket
import com.canerture.booksapp.data.model.Order
import com.canerture.booksapp.data.model.UserModel

@Dao
interface BooksBasketDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBookBasket(bookBasket: BookBasket)

    @Query("SELECT * FROM booksbasketdatabase")
    fun getBooksBasket(): List<BookBasket>?

    @Query("DELETE FROM booksbasketdatabase WHERE id = :idInput")
    fun deleteBookWithId(idInput: Int)

    @Query("DELETE FROM booksbasketdatabase")
    fun clearBasket()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: UserModel): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addOrder(order: Order)

    @Transaction
    @Query("SELECT * FROM orders WHERE userId = :userId")
    fun getOrdersForUser(userId: Int): List<OrderWithBooks>

    @Transaction
    @Query("SELECT * FROM orders")
    fun getAllOrders(): List<OrderWithBooks>
}

data class OrderWithBooks(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "bookId",
        entityColumn = "id"
    )
    val book: BookBasket
)
