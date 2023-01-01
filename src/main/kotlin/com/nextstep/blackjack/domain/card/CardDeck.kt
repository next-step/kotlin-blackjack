package com.nextstep.blackjack.domain.card

class CardDeck {
    private val cards: MutableList<Card> =
        CardNumber.values().flatMap { number -> CardPattern.values().map { pattern -> Card(number, pattern) } }.shuffled().toMutableList()

    fun draw(): Card = cards.removeFirst()
}
