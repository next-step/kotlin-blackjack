package blackjack.participant

import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.game.result.ParticipantPlayResult
import blackjack.domain.state.FinishState
import blackjack.domain.state.RunningState
import blackjack.domain.state.State

typealias DrawingEvent = () -> Card
private typealias PlayEvent = (RunningState) -> ParticipantPlayResult

sealed class Participant(protected var state: State) {

    protected fun playByState(playEvent: PlayEvent): ParticipantPlayResult = when (val playState = state) {
        is RunningState -> playEvent(playState)

        is FinishState -> ParticipantPlayResult(
            participant = this,
            finishState = playState,
        )
    }

    protected fun stayState(runningState: RunningState): ParticipantPlayResult {
        val finishState = runningState.stay()
        state = finishState
        return ParticipantPlayResult(participant = this, finishState = finishState)
    }

    fun getCards(): PlayingCards = state.playingCards

    abstract fun getName(): String
}
