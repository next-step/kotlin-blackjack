package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.Started
import blackjack.domain.state.State

class Player(
    override val name: String,
    override val cards: Cards = Cards(),
) : Participant() {
    var state: State = Started(cards)
        private set

    override fun receiveCard(card: Card) {
        state = state.draw(card)
    }

    fun turnStand() {
        check(state is Hit) { "Hit 상태가 아닙니다." }
        state = (state as Hit).stand()
    }

    infix fun versus(dealer: Dealer): GameResult {
        return GameResult.resultOfPlayer(this, dealer)
    }
}
