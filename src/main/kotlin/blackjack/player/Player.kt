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

    fun addAll(cards: List<Card>) {
        cards.forEach { this.add(it) }
    }

    fun add(card: Card) {
        this.deck.add(card)
    }

    fun cardNames(): String {
        return this.deck.cards.joinToString(", ") { it.fullName() }
    }

    fun sumPoint(): Int {
        return this.deck.pointSum
    }
}
