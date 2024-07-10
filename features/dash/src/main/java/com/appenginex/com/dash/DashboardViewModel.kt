package com.appenginex.com.dash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appenginex.com.model.CreditCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {
    private val _cards = MutableStateFlow<List<CreditCard>>(emptyList())
    val cards: StateFlow<List<CreditCard>> = _cards

    init {
        loadCreditCards()
    }

    private fun loadCreditCards() {
        // Simulate loading cards from a data source
        viewModelScope.launch {
            _cards.value = listOf(
                CreditCard("Cozma Nicolae", "*****************", "0224", "123"),
                CreditCard("John Doe", "*****************", "1123", "456"),
                CreditCard("Jane Smith", "*****************", "0522", "7")
            )
        }
    }

}