package com.appenginex.com.creditcard.info

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appenginex.com.common.getCurrentTime
import com.appenginex.com.creditcard.utils.readLogsFromFile
import com.appenginex.com.creditcard.utils.writeLogsToFile
import com.appenginex.com.data.CreditCards
import com.appenginex.com.model.CreditCard
import com.appenginex.com.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoCardViewModel @Inject constructor() : ViewModel() {
    private val _cards = MutableStateFlow<List<CreditCard>>(emptyList())
    val cards: StateFlow<List<CreditCard>> = _cards

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> get() = _transactions

    init {
         loadCreditCards()
    }

    fun readLogs(context: Context, filter: String? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            val logs = readLogsFromFile(context)
            _transactions.value = logs.filter { filter == it.cardNumber }
        }
    }


    private fun loadCreditCards() {
        viewModelScope.launch {
            _cards.value = CreditCards.cards
        }
    }


    fun makeTransaction(context: Context, amount: String, recipient: CreditCard?, currentCardNumber:String) {
        if (recipient != null) {
            val newTransaction = Transaction(
                time = getCurrentTime(),
                amount = amount,
                recipientName = recipient.cardHolderName,
                cardNumber = currentCardNumber
            )
            val array = _transactions.value.toMutableList()
            array.add(newTransaction)
            _transactions.value = array.toList()
            writeLogsToFile(context = context, logs = array)
        }
    }

}