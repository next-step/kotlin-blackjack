package blackjack.domain.player

import blackjack.domain.card.Card

class PlayerCards {

    private val cards = mutableListOf<Card>()
    val values: List<Card>
        get() = cards

    fun add(card: Card) {
        cards.add(card)
    }
}
