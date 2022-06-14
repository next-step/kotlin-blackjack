package blackjack.player

import blackjack.card.Card

class Player(val playerName: String) {
    private val deck = PlayerDeck()

    fun addAll(cards: List<Card>) {
        cards.forEach { this.add(it) }
    }

    fun add(card: Card) {
        this.deck.add(card)
    }

    fun cards(): List<Card> {
        return this.deck.cards
    }

    fun sumPoint(): Int {
        return this.deck.pointSum
    }

    companion object {
        fun of(name: String, cards: List<Card>): Player {
            val player = Player(name)
            cards.forEach { card ->
                player.add(card)
            }

            return player
        }
    }
}
