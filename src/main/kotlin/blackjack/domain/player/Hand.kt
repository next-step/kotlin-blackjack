package blackjack.domain.player

import blackjack.domain.card.Card

data class Hand(
    val cards: MutableList<Card> = mutableListOf()
) {

    fun add(card: Card): Hand {
        cards.add(card)
        return this
    }

    fun score(): Int {
        return cards
            .sortedBy { it.symbol }
            .fold(0) { acc, card -> acc + card.count(acc) }
    }
}
