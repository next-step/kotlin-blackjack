package blackjack.domain.participant

import blackjack.domain.game.BlackJack

class Player(
    name: String,
    state: State = State(),
    private var bettingAmount: Int = 0
) : Participant(name, state) {

    override var continueDrawing: Boolean = false

    fun finishedTurn() {
        continueDrawing = true
    }

    fun canProceedTurn() = !continueDrawing && state.score() <= BlackJack.BLACKJACK_MAX_SCORE

    fun bet(amount: Int) {
        this.bettingAmount = amount
    }

    fun getRevenue(dealer: Dealer): Int {
        return state.getRank(dealer.state) * bettingAmount
    }
}
