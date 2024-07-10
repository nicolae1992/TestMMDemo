package com.appenginex.com.creditcard.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appenginex.com.common.Constants.EMPTY_TEXT
import com.appenginex.com.data.repository.CardRepository
import com.appenginex.com.model.CreditCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateCardViewModel @Inject constructor(
    private val repository: CardRepository
) : ViewModel() {
    private val _msg = MutableStateFlow(EMPTY_TEXT)
    val msg: StateFlow<String> = _msg

    fun addCard(creditCard: CreditCard) {
        viewModelScope.launch {
            repository.addCreditCardStorage(creditCard)
            _msg.value = "Your creditCard has been added"
        }
    }

    fun clearMessage() {
        _msg.value = ""
    }

}