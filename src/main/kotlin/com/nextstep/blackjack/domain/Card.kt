package com.nextstep.blackjack.domain

data class Card(val symbol: Symbol, val cardNumber: CardNumber) {
    fun getNumber(): Int {
        return cardNumber.number
    }
}
