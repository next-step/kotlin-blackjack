package domain.turn

import domain.Result
import domain.card.CardDeck
import domain.card.ShuffledCardDeck
import domain.gamer.Gamers

class Game(
    initialTurn: Turn,
    val gamers: Gamers,
    private val cardDeck: CardDeck = ShuffledCardDeck.createNew(),
    private val askPlayerWantToStay: ((String) -> Boolean)? = null,
) {
    private var turn = initialTurn

    val isFinish: Boolean
        get() = turn is FinalTurn

    val result: Result?
        get() {
            val capturedTurn = turn
            if (capturedTurn is FinalTurn) return capturedTurn.result(gamers)
            return null
        }

    fun proceed() {
        turn.proceed(gamers, cardDeck, ::changeState, askPlayerWantToStay)
    }

    private fun changeState(turn: Turn) {
        this.turn = turn
    }
}
