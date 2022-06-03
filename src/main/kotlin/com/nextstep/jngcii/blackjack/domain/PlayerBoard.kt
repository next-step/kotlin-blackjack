package com.nextstep.jngcii.blackjack.domain

import com.nextstep.jngcii.blackjack.INITIAL_COUNT

class PlayerBoard(val playerName: String) {
    private val _cards = mutableListOf<Card>()
    val cards get() = _cards.toList()
    val total get() = cards.sumOf { it.symbol.value }

    fun ready(deck: CardDeck) {
        repeat(INITIAL_COUNT) {
            addCard(deck.pop())
        }
    }

    fun addCard(card: Card) {
        if (_cards.contains(card)) {
            throw IllegalArgumentException("이미 존재하는 카드입니다")
        }
        _cards.add(card)
    }
}
