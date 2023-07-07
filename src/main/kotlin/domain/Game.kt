package domain

import domain.card.CardDeck
import domain.card.ShuffledCardDeck

class Game(val players: List<Player>, private val cardDeck: CardDeck = ShuffledCardDeck.createNew()) {

    fun start() {
        players.forEach {
            it.hit(cardDeck.pop())
            it.hit(cardDeck.pop())
        }
    }

    fun playersCanReceiveMoreCard(): List<Player> {
        return players.filter {
            it.canReceiveMoreCard()
        }
    }

    fun hit(player: Player) {
        player.hit(cardDeck.pop())
    }
}
