package blackjack.domain.player

import blackjack.domain.behavior.State
import blackjack.domain.card.Card
import blackjack.domain.card.InitPlayingCards
import blackjack.domain.game.GameEvent
import blackjack.domain.model.BlackJackErrorCode

private typealias DrawingEvent = () -> Card

class Player(val name: String, state: State) {

    var state: State = state
        private set

    init {
        require(value = name.length in NAME_LENGTH_RANGE) {
            BlackJackErrorCode.CAN_NOT_USED_RANGE_OF_NAME_LENGTH.message(
                arrayOf(NAME_LENGTH_RANGE, name)
            )
        }
    }

    tailrec fun play(gameEvent: GameEvent, drawingEvent: DrawingEvent): Unit = when {
        state.availableTurn() && gameEvent.hitOrNot(name) -> {
            state = state.hit(card = drawingEvent())
            gameEvent.resultEvent(this)
            play(gameEvent = gameEvent) { drawingEvent() }
        }

        state.availableTurn().not() -> Unit

        state.playingCards.size == InitPlayingCards.INIT_CARD_COUNT -> {
            gameEvent.resultEvent(this)
            state = state.stay()
        }

        else -> state = state.stay()
    }

    companion object {
        private val NAME_LENGTH_RANGE: IntRange = 1..10
    }
}
