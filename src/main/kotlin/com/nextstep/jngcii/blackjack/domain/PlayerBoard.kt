package com.nextstep.jngcii.blackjack.domain

class PlayerBoard {
    private val _cards = mutableListOf<Card>()
    val cards get() = _cards.toList()
    val total get() = cards.sumOf { it.symbol.value }

    fun addCard(card: Card) {
        if (_cards.contains(card)) {
            throw IllegalArgumentException("이미 존재하는 카드입니다")
        }
        _cards.add(card)
    }
}
