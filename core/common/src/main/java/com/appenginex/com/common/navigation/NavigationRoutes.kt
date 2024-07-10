package com.appenginex.com.common.navigation

object NavigationRoutes {
    const val InfoCreditCardRoute = "info_credit_card"

    object InfoCreditCard {
        const val argCardHolderName = "cardHolderName"
        const val argCardNumber = "cardNumber"
        const val argExpiryDate = "expiryDate"
        const val argCvv = "cvv"

        const val INFO_CREDIT_CARD =
            "$InfoCreditCardRoute/{$argCardHolderName}/{$argCardNumber}/{$argExpiryDate}/{$argCvv}"

        fun infoCreditCardWithArg(
            holderName: String,
            cardNumber: String,
            expiryDate: String,
            cvv: String
        ): String = "$InfoCreditCardRoute/$holderName/$cardNumber/$expiryDate/$cvv"
    }

    object CreateCreditCard {
        const val CREATE_CREDIT_CARD = "create_credit_card"

        fun createCreditCard(): String = CREATE_CREDIT_CARD
    }
}