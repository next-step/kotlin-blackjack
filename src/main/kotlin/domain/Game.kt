package domain

import domain.card.CardDeck
import domain.card.CardDeckImpl

class Game(val players: List<Player>, private val cardDeck: CardDeck = CardDeckImpl()) {

    fun start() {
        players.dealInitialCards()
    }

    fun playersCanReceiveMoreCard(): List<Player> {
        return players.filter {
            it.canReceiveMoreCard()
        }
    }

    fun dealAdditionalCard(player: Player) {
        player.dealCard()
    }

    private fun List<Player>.dealInitialCards() {
        forEach {
            it.dealInitialCards()
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
                hit(card)
            }
    }
}
