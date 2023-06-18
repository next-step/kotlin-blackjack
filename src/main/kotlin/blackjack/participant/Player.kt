package blackjack.participant

import blackjack.domain.game.result.ParticipantPlayResult
import blackjack.domain.state.RunningState
import blackjack.domain.state.State
import blackjack.event.PlayerEvent

class Player(private val participantName: ParticipantName, state: State) : Participant(state = state) {

    fun play(playerEvent: PlayerEvent, drawingEvent: DrawingEvent): ParticipantPlayResult = playByState(
        playEvent = {
            hit(playerEvent = playerEvent, runningState = it, drawingEvent = drawingEvent)
        },
    )

    private fun hit(
        playerEvent: PlayerEvent,
        runningState: RunningState,
        drawingEvent: DrawingEvent,
    ): ParticipantPlayResult = if (playerEvent.hitOrNot(getName())) {
        state = runningState.draw(card = drawingEvent())
        playerEvent.resultEvent(this)
        play(playerEvent = playerEvent) { drawingEvent() }
    } else {
        stayState(runningState = runningState)
    }

    override fun getName(): String = participantName.name
}
