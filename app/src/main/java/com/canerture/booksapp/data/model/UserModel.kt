package com.canerture.booksapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//data class UserModel(
//    val email: String?,
//    val nickname: String?,
//    val phoneNumber: String?,
//)
@Entity(tableName = "users")
data class UserModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId")
    val userId: Int = 0,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "nickname")
    val nickname: String?,

    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String?
)
