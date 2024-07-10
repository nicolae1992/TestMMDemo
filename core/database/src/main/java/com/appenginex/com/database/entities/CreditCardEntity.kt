package com.appenginex.com.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("creditCards")
data class CreditCardEntity(
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @ColumnInfo(name = "cardHolderName")
    val cardHolderName: String,
    @ColumnInfo(name = "cardNumber")
    val cardNumber: String,
    @ColumnInfo(name = "expiryDate")
    val expiryDate: String,
    @ColumnInfo(name = "cvv")
    val cvv: String
)
