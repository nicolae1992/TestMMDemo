package com.appenginex.com.dash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appenginex.com.data.CreditCards
import com.appenginex.com.data.repository.CardRepository
import com.appenginex.com.model.CreditCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: CardRepository
) : ViewModel() {
    private val _cards = MutableStateFlow<List<CreditCard>>(emptyList())
    val cards: StateFlow<List<CreditCard>> = _cards

    init {
        getAllCards()
    }

    private fun getAllCards() {
        viewModelScope.launch {
            repository.getCreditCards().collect {
                if (it.isEmpty()) {
                    _cards.value = CreditCards.cards
                }else {
                    _cards.value = it
                }
            }
        }
    }

}