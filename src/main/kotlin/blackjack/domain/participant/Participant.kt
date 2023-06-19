package blackjack.domain.participant

import blackjack.domain.bet.Bet
import blackjack.domain.card.Card
import blackjack.domain.card.PlayingCards
import blackjack.domain.game.result.ParticipantPlayResult
import blackjack.domain.state.FinishState
import blackjack.domain.state.RunningState
import blackjack.domain.state.State

typealias DrawingEvent = () -> Card
typealias HitEvent = () -> Boolean
private typealias PlayEvent = () -> Unit
private typealias PlayResultEvent = () -> ParticipantPlayResult
private typealias StayEvent = () -> FinishState

sealed class Participant(private var state: State, private var bet: Bet) {

    protected fun playByState(
        hitEvent: HitEvent,
        drawingEvent: DrawingEvent,
        playEvent: PlayEvent,
    ): ParticipantPlayResult = when (val playState = state) {
        is RunningState -> hit(hitEvent = hitEvent, stayEvent = { playState.stay() }) {
            state = playState.draw(card = drawingEvent())
            playEvent()
            playByState(hitEvent = hitEvent, drawingEvent = drawingEvent, playEvent = playEvent)
        }

        is FinishState -> ParticipantPlayResult(
            participant = this,
            finishState = playState,
        )
    }

    private fun hit(
        hitEvent: HitEvent,
        stayEvent: StayEvent,
        playResultEvent: PlayResultEvent,
    ): ParticipantPlayResult = if (hitEvent()) {
        playResultEvent()
    } else {
        val finishState = stayEvent()
        state = finishState
        ParticipantPlayResult(participant = this, finishState = finishState)
    }

    fun getCards(): PlayingCards = state.playingCards

    abstract fun getName(): String
}
