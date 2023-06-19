package blackjack.domain.participant

import blackjack.domain.bet.Bet
import blackjack.domain.game.result.ParticipantPlayResult
import blackjack.domain.state.State
import blackjack.event.PlayerEvent

class Player(
    private val participantName: ParticipantName,
    bet: Bet,
    state: State,
) : Participant(state = state, bet = bet) {

    fun play(playerEvent: PlayerEvent, drawingEvent: DrawingEvent): ParticipantPlayResult = playByState(
        hitEvent = { playerEvent.hitOrNot(getName()) },
        drawingEvent = drawingEvent,
        playEvent = { playerEvent.resultEvent(this) },
    )

    override fun getName(): String = participantName.name
}
