package com.nextstep.blackjack.domain.card

fun List<Card>.deepCopy(): List<Card> = map { it.copy() }

class CardFactory {
    companion object {
        private val cards: List<Card> =
            CardNumber.values().flatMap { number -> CardPattern.values().map { pattern -> Card(number, pattern) } }

        fun getCards(): List<Card> = cards.deepCopy().shuffled()
    }
}
