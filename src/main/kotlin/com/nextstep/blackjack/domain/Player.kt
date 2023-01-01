package com.nextstep.blackjack.domain

import com.nextstep.blackjack.domain.card.Card

private fun List<Card>.deepCopy(): List<Card> = map { it.copy() }

class Player(val name: String, cards: List<Card>) {
    private val _cards: MutableList<Card> = cards.deepCopy().toMutableList()
    val cards: List<Card>
        get() = _cards.deepCopy()

    constructor(name: String) : this(name, emptyList())

    init {
        require(name.isNotBlank()) { "이름은 빈 값일 수 없습니다." }
    }

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun calculateScore(): Int {
        val baseScore = _cards.sumOf { it.score() }
        var calculatedScore = baseScore

        val aceCount = _cards.count { it.isAce() }
        repeat(aceCount) {
            if (calculatedScore + 10 <= 21) calculatedScore += 10
        }

        return calculatedScore
    }
}
