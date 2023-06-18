package blackjack.participant

import blackjack.domain.state.FinishState
import blackjack.domain.state.RunningState
import blackjack.domain.state.State
import blackjack.event.GameEvent
import blackjack.participant.player.PlayerName
import blackjack.participant.player.PlayerResult

class Player(playerName: PlayerName, state: State) : Participant<GameEvent>(playerName = playerName, state = state) {

    override fun play(gameEvent: GameEvent, drawingEvent: DrawingEvent): PlayerResult =
        when (val playState = state) {
            is RunningState -> hit(
                gameEvent = gameEvent,
                runningState = playState,
                drawingEvent = drawingEvent,
            )

            is FinishState -> PlayerResult(
                player = this,
                score = playState.resultScore(),
            )
        }

    private fun hit(
        gameEvent: GameEvent,
        runningState: RunningState,
        drawingEvent: DrawingEvent,
    ): PlayerResult = if (gameEvent.hitOrNot(getName())) {
        state = runningState.draw(card = drawingEvent())
        gameEvent.resultEvent(this)
        play(gameEvent = gameEvent) { drawingEvent() }
    } else {
        val finishState = runningState.stay()
        state = finishState
        PlayerResult(player = this, score = finishState.resultScore())
    }
}
