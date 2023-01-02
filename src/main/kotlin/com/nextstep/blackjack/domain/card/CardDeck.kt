package com.nextstep.blackjack.domain.card

import com.nextstep.blackjack.domain.Player

class CardDeck {
    private val cards: MutableList<Card> =
        CardNumber.values().flatMap { number -> CardPattern.values().map { pattern -> Card(number, pattern) } }.shuffled().toMutableList()

    fun deal(vararg players: Player) {
        players.map { it.addCard(cards.removeFirst()) }
    }
}
