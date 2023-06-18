package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.game.GameEvent
import blackjack.domain.state.RunningState
import blackjack.domain.state.State

private typealias DrawingEvent = () -> Card

class Player(private val playerName: PlayerName, private var state: State) {

    tailrec fun play(gameEvent: GameEvent, drawingEvent: DrawingEvent) {
        val runningState = state as? RunningState

        when {
            runningState == null -> Unit

            gameEvent.hitOrNot(playerName.name) -> {
                state = runningState.draw(card = drawingEvent())
                gameEvent.resultEvent(this)
                play(gameEvent = gameEvent) { drawingEvent() }
            }

            else -> {
                state = runningState.stay()
            }
        }
    }

    fun getName() = playerName.name
}
