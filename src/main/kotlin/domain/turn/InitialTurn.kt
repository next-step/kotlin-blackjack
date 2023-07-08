package domain.turn

import domain.card.CardDeck
import domain.card.ShuffledCardDeck
import domain.player.Players

class InitialTurn(private val players: Players, private val cardDeck: CardDeck = ShuffledCardDeck.createNew()) {
    fun proceed(): Turn {
        players.hit(cardDeck)
        players.hit(cardDeck)
        return Turn(players, cardDeck)
    }
}
