package com.appenginex.com.dash


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.appenginex.com.common.navigation.NavigationRoutes
import com.appenginex.com.designsystem.PaymentCard

@Composable
fun DashboardScreen(
    navHostController: NavHostController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    val cards by viewModel.cards.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(cards) { card ->
                PaymentCard(
                    TextFieldValue(card.cardHolderName),
                    TextFieldValue(card.cardNumber),
                    TextFieldValue(card.expiryDate),
                    TextFieldValue(card.cvv)
                ) {
                    navHostController.navigate(
                        NavigationRoutes.InfoCreditCard.infoCreditCardWithArg(
                            card.cardHolderName,
                            card.cardNumber,
                            card.expiryDate,
                            card.cvv
                        )
                    )
                }
            }
        }


        FloatingActionButton(
            onClick = {
                navHostController.navigate(NavigationRoutes.CreateCreditCard.createCreditCard())
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            containerColor = MaterialTheme.colorScheme.primary
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Card")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DashBoardScreenPreview() {
    val navController = rememberNavController()
    DashboardScreen(navHostController = rememberNavController())
}