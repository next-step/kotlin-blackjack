package com.nextstep.blackjack.domain

data class Card(val symbol: Symbol, val cardNumber: CardNumber) {
    override fun toString(): String {
        return cardNumber.number.toString() + symbol.symbol
    }

    companion object {
        const val BLACKJACK_NUMBER = 21
        const val BLACKJACK_ACE_JUDGE_THRESHOLD = 11
        const val BLACKJACK_ACE_CORRECTION = 10
    }
}
