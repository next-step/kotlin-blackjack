package com.nextstep.blackjack.domain.card

import com.nextstep.blackjack.domain.Player

class CardDeck(cards: List<Card>) {
    private val cards: MutableList<Card> = cards.deepCopy().toMutableList()

    constructor() : this(CardFactory.getCards())

    fun deal(vararg players: Player) {
        players.map { it.addCard(cards.removeFirst()) }
    }
}
