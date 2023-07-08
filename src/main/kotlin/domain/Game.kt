package domain

import domain.card.CardDeck
import domain.card.ShuffledCardDeck
import domain.player.Player
import domain.player.Players

class Game(private val cardDeck: CardDeck = ShuffledCardDeck.createNew()) {

    fun start(players: Players) {
        players.hit(cardDeck)
        players.hit(cardDeck)
    }

    fun playersCanReceiveMoreCard(players: Players): Players {
        return players.playersCanReceiveMoreCard()
    }

    fun hit(players: Players, afterHit: ((Player) -> Unit)? = null) {
        players.hit(cardDeck, afterHit)
    }
}
