package domain

import domain.card.CardDeck

class Game(val players: List<Player>) {

    private val cardDeck = CardDeck()

    fun start() {
        players.forEach { player ->
            player.dealInitialCards()
        }
    }

    private fun Player.dealInitialCards() {
        repeat(2) {
            this.dealCard()
        }
    }

    private fun Player.dealCard() {
        cardDeck.pop()
            ?.let { card ->
                dealCard(card)
            }
    }
}
