package blackjack.participant

import blackjack.domain.game.result.ParticipantPlayResult
import blackjack.domain.state.RunningState
import blackjack.domain.state.State
import blackjack.event.DealerEvent

class Dealer(state: State) : Participant(state = state) {

    fun play(dealerEvent: DealerEvent, drawingEvent: DrawingEvent): ParticipantPlayResult = playByState(
        playEvent = {
            hit(dealerEvent = dealerEvent, runningState = it, drawingEvent = drawingEvent)
        },
    )

    private fun hit(
        runningState: RunningState,
        drawingEvent: DrawingEvent,
        dealerEvent: DealerEvent,
    ) = if (getCards().calculateAceOptimalScore() <= ADD_DRAW_CONDITIONS) {
        state = runningState.draw(card = drawingEvent())
        dealerEvent.hitEvent()
        play(dealerEvent = dealerEvent, drawingEvent = drawingEvent)
    } else {
        stayState(runningState = runningState)
    }

    override fun getName(): String = DEALER_NAME

    companion object {
        private const val DEALER_NAME: String = "딜러"
        private const val ADD_DRAW_CONDITIONS: Int = 16
    }
}
