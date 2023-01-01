package com.nextstep.blackjack.domain

import com.nextstep.blackjack.domain.card.Card

private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }

class Player(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.deepCopy()

    init {
        require(name.isNotBlank()) { "이름은 빈 값일 수 없습니다." }
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
