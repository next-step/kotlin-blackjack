package blackjack.domain.player

import blackjack.domain.card.Card

class Player(val name: String) {
    val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }
}
