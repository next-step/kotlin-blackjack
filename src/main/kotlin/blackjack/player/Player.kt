package blackjack.player

import blackjack.card.Card

class Player(val playerName: String) {
    private val deck = PlayerDeck()

    fun add(card: Card) {
        this.deck.add(card)
    }

    fun cardNames(): String {
        return this.deck.cards.joinToString(", ") { it.fullName() }
    }
}
