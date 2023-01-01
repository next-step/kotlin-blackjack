package com.nextstep.blackjack.domain.card

class CardDeck {
    private val cards: List<Card> =
        CardNumber.values().flatMap { number -> CardPattern.values().map { pattern -> Card(number, pattern) } }.shuffled()

    fun draw(): Card = cards.first()
}
