package blackjack.domain.participant

import blackjack.domain.bet.Bet
import blackjack.domain.game.result.ParticipantPlayResult
import blackjack.domain.state.State
import blackjack.event.DealerEvent

class Dealer(state: State) : Participant(state = state, bet = Bet()) {

    fun play(dealerEvent: DealerEvent, drawingEvent: DrawingEvent): ParticipantPlayResult = playByState(
        hitEvent = { getCards().calculateAceOptimalScore() <= ADD_DRAW_CONDITIONS },
        drawingEvent = drawingEvent,
        playEvent = dealerEvent.hitMessageEvent,
    )

    override fun getName(): String = DEALER_NAME

    companion object {
        private const val DEALER_NAME: String = "딜러"
        private const val ADD_DRAW_CONDITIONS: Int = 16
    }
}
