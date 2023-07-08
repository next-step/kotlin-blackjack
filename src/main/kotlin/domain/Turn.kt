package domain

import domain.card.CardDeck
import domain.card.ShuffledCardDeck
import domain.player.Players

data class Turn(val players: Players, private val cardDeck: CardDeck = ShuffledCardDeck.createNew()) {

    fun proceedInitialTurn() {
        players.hit(cardDeck)
        players.hit(cardDeck)
    }

    fun playersCanTakeNextTurn(): Players {
        return players.playersCanReceiveMoreCard()
    }

    fun proceedNextTurn(players: Players): Turn {
        return copy(players = players).apply { hit() }
    }

    private fun hit() {
        players.hit(cardDeck)
    }
}
