package com.canerture.booksapp.data.repos

import com.canerture.booksapp.common.Resource
import com.canerture.booksapp.data.model.Book
import com.canerture.booksapp.data.model.BookBasket
import com.canerture.booksapp.data.model.Order
import com.canerture.booksapp.data.model.UserModel
import com.canerture.booksapp.data.retrofit.BooksService
import com.canerture.booksapp.data.room.BooksBasketDAO
import com.canerture.booksapp.data.room.OrderWithBooks

class BooksRepository(
    private val booksService: BooksService,
    private val booksDAO: BooksBasketDAO
) {

    suspend fun books(): Resource<List<Book>> {
        return try {
            val response = booksService.allBooks()
            if (response.isSuccessful) {
                Resource.Success(response.body()?.books.orEmpty())
            } else {
                Resource.Fail(response.message())
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    fun booksBasket(): Resource<List<BookBasket>> {
        return try {
            val response = booksDAO.getBooksBasket()
            if (response.isNullOrEmpty()) {
                Resource.Fail("Sepetinizde kitap bulunmamaktadır.")
            } else {
                Resource.Success(response)
            }
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    fun addBookToBasket(book: Book) {
        val bookBasket = BookBasket(
            name = book.name,
            author = book.author,
            publisher = book.publisher,
            price = book.price,
            imageUrl = book.imageUrl
        )
        booksDAO.addBookBasket(bookBasket)
    }

    fun deleteBookFromBasket(bookId: Int) = booksDAO.deleteBookWithId(bookId)

    fun clearBasket() = booksDAO.clearBasket()

    fun placeOrder(user: UserModel, books: List<BookBasket>, quantity: Int) {
        val userId = booksDAO.addUser(user).toInt()
        val currentTime = System.currentTimeMillis()

        books.forEach { book ->
            val order = Order(
                userId = userId,
                bookId = book.id,
                name = book.name,
                price = book.price,
                quantity = quantity,
                orderDate = currentTime
            )
            booksDAO.addOrder(order)
        }
    }

    fun getOrdersForUser(userId: Int): List<OrderWithBooks> {
        return booksDAO.getOrdersForUser(userId)
    }

    fun getAllOrders(): List<OrderWithBooks> {
        return booksDAO.getAllOrders()
    }
}
