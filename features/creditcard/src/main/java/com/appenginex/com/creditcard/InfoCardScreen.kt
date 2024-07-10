package com.appenginex.com.creditcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.appenginex.com.designsystem.PaymentCard
import com.appenginex.com.model.CreditCard

@Composable
fun InfoCardScreen(
    modifier: Modifier = Modifier,
    creditCard: CreditCard
) {
    Box(modifier = Modifier.fillMaxSize()) {
        PaymentCard(
            TextFieldValue(creditCard.cardHolderName),
            TextFieldValue(creditCard.cardNumber),
            TextFieldValue(creditCard.expiryDate),
            TextFieldValue(creditCard.cvv)
        ) {}
    }
}

@Preview
@Composable
private fun InfoCardScreenPreview() {

    InfoCardScreen(creditCard = CreditCard("Cozma Nicolae", "*****************", "0224", "123"),)
}