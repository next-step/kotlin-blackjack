package domain

import domain.card.CardDeck
import domain.card.ShuffledCardDeck

class Game(val players: List<Player>, private val cardDeck: CardDeck = ShuffledCardDeck.createNew()) {

    fun start() {
        players.hitInitialCards()
    }

    fun playersCanReceiveMoreCard(): List<Player> {
        return players.filter {
            it.canReceiveMoreCard()
        }
    }

    fun hit(player: Player) {
        player.hit(cardDeck.pop())
    }

    private fun List<Player>.hitInitialCards() {
        forEach {
            it.hitInitialCards()
        }
    }

    private fun Player.hitInitialCards() {
        repeat(2) {
            hit(cardDeck.pop())
        }
    }
}
