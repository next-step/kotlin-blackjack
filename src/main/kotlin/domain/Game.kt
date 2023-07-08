package domain

import domain.card.CardDeck
import domain.card.ShuffledCardDeck
import domain.player.Players

class Game(players: Players, private val cardDeck: CardDeck = ShuffledCardDeck.createNew()) {

    var players: Players = players.duplicate()

    fun start() {
        players.hit(cardDeck)
        players.hit(cardDeck)
    }

    fun playersCanReceiveMoreCard(): Players {
        players = players.playersCanReceiveMoreCard()
        return players.duplicate()
    }

    fun hit() {
        players.hit(cardDeck)
    }

    fun updateRemainPlayers(players: Players) {
        this.players = players
    }
}
