package com.appenginex.com.data.extension

import com.appenginex.com.common.Constants.EMPTY_TEXT
import com.appenginex.com.database.entities.CreditCardEntity
import com.appenginex.com.model.CreditCard
import com.appenginex.com.model.DeleteCreditCard
import com.appenginex.com.model.CreditCardInfo
import com.appenginex.com.model.Transaction
import com.appenginex.com.network.model.NetworkAddCreditCard
import com.appenginex.com.network.model.NetworkDeleteCard
import com.appenginex.com.network.model.NetworkTransaction

fun NetworkAddCreditCard.toPostCreditCard(defaultType: String = EMPTY_TEXT): CreditCardInfo {
    return CreditCardInfo(
        cardNumber = this.cardNumber,
        customerName = "",
        cvv = "",
        expiry = "",
        cardType = type?:defaultType
        )
}

fun NetworkDeleteCard.toDeleteCreditCard(defaultUrl: String = EMPTY_TEXT): DeleteCreditCard {
    return DeleteCreditCard(type = this.type ?: defaultUrl)
}

fun NetworkTransaction.toTransaction(): Transaction {
    return Transaction()
}


fun CreditCardEntity.toCreditCard(): CreditCard {
    return CreditCard(
        cardNumber = this.cardNumber,
        cardHolderName = this.cardHolderName,
        cvv = this.cvv,
        expiryDate = this.expiryDate
    )
}

fun CreditCard.toCreditCardEntity(): CreditCardEntity {
    return CreditCardEntity(
        cardNumber = this.cardNumber,
        cardHolderName = this.cardHolderName,
        cvv = this.cvv,
        expiryDate = this.expiryDate
    )
}

