package blackjack.domain.participant

class Dealer(state: State = State()) : Participant(DEALER_NAME, state) {
    override val continueDrawing: Boolean = state.score() <= DEALER_CARD_STANDARD_SCORE

    companion object {
        private const val DEALER_CARD_STANDARD_SCORE = 16
        private const val DEALER_NAME = "딜러"
    }
}
