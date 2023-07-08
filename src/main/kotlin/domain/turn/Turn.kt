package domain.turn

import domain.card.CardDeck
import domain.player.Players

data class Turn(internal val players: Players, internal val cardDeck: CardDeck) {

    fun playersCanTakeNextTurn(): Players {
        return players.playersCanReceiveMoreCard()
    }

    fun proceed(players: Players): Turn {
        return copy(players = players).apply { hit() }
    }

    private fun hit() {
        players.hit(cardDeck)
    }
}
