package com.appenginex.com.creditcard.info

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.appenginex.com.designsystem.PaymentCard
import com.appenginex.com.designsystem.TransactionLogItem
import com.appenginex.com.model.CreditCard
import kotlinx.coroutines.launch

@Composable
fun InfoCardScreen(
    modifier: Modifier = Modifier,
    viewModel: InfoCardViewModel = hiltViewModel(),
    creditCard: CreditCard
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCard by remember { mutableStateOf<CreditCard?>(null) }
    var amount by remember { mutableStateOf(TextFieldValue("")) }
    val cards by viewModel.cards.collectAsState()
    val transactions by viewModel.transactions.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        viewModel.readLogs(context, creditCard.cardNumber)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            PaymentCard(
                TextFieldValue(creditCard.cardHolderName),
                TextFieldValue(creditCard.cardNumber),
                TextFieldValue(creditCard.expiryDate),
                TextFieldValue(creditCard.cvv)
            ) {}

            Text("Select a card for the transaction:")

            Box(modifier = Modifier.fillMaxWidth()) {
                Button(onClick = { expanded = true }) {
                    Text(text = selectedCard?.cardHolderName ?: "Choose a card")
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    cards.forEach { card ->
                        DropdownMenuItem(
                            text = { Text(text = card.cardHolderName) },
                            onClick = {
                                selectedCard = card
                                expanded = false
                            },
                        )
                    }
                }
            }

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {
                    if (selectedCard == null) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please select a card")
                        }
                    } else {
                        viewModel.makeTransaction(
                            context = context,
                            amount.text,
                            selectedCard,
                            currentCardNumber = creditCard.cardNumber
                        )
                    }
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Make Transaction")
            }

            Text("Transaction Logs:")

            transactions.forEach { transaction ->
                TransactionLogItem(transaction = transaction)
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview
@Composable
private fun InfoCardScreenPreview() {

    InfoCardScreen(creditCard = CreditCard("Cozma Nicolae", "*****************", "0224", "123"))
}