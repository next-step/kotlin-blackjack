package com.nextstep.blackjack.domain

import com.nextstep.blackjack.domain.card.Card
import com.nextstep.blackjack.domain.card.deepCopy

private const val TWENTY_ONE = 21
private const val EXTRA_SCORE_FOR_ACE = 10

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
            if (calculatedScore + EXTRA_SCORE_FOR_ACE <= TWENTY_ONE) calculatedScore += EXTRA_SCORE_FOR_ACE
        }

        return calculatedScore
    }

    fun canTakeMoreCard(): Boolean = calculateScore() < TWENTY_ONE
}
