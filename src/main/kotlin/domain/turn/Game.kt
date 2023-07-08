package domain.turn

import domain.Result
import domain.card.CardDeck
import domain.card.ShuffledCardDeck
import domain.dealer.Dealer
import domain.player.Players

class Game(
    initialTurn: Turn,
    val dealer: Dealer,
    val players: Players,
    private val cardDeck: CardDeck = ShuffledCardDeck.createNew()
) {
    private var turn = initialTurn

    val isFinish: Boolean
        get() = turn is FinalTurn

    val result: Result?
        get() {
            val capturedTurn = turn
            if (capturedTurn is FinalTurn) return capturedTurn.result(dealer, players)
            return null
        }

    fun proceed() {
        turn.proceed(dealer, players, cardDeck, ::changeState)
    }

    fun playersCanTakeNextTurn(): Players {
        return players.playersCanReceiveMoreCard()
    }

    private fun changeState(turn: Turn) {
        this.turn = turn
    }
}
