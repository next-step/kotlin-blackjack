package blackjack.player

import blackjack.card.Card

class Player(val playerName: String) {
    private val deck = PlayerCardDeck()

    fun add(card: Card) {
        this.deck.add(card)
    }
}
