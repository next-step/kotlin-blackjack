package domain

import domain.card.CardDeck
import domain.card.CardDeckImpl

class Game(val players: List<Player>, private val cardDeck: CardDeck = CardDeckImpl()) {

    fun start() {
        players.forEach { player ->
            player.dealInitialCards()
        }
    }

    fun playersCanReceiveMoreCard(): List<Player> {
        return players.filter {
            it.canReceiveMoreCard()
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
