package blackjack.player

import blackjack.card.Card

class Player(val playerName: String) {
    private val deck = PlayerDeck()

    companion object {
        fun of(name: String, cards: List<Card>): Player {
            val player = Player(name)
            cards.forEach { card ->
                player.add(card)
            }

            return player
        }
    }

    fun add(card: Card) {
        this.deck.add(card)
    }

    fun cardNames(): String {
        return this.deck.cards.joinToString(", ") { it.fullName() }
    }
}
