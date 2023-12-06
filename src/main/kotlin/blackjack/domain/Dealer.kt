package blackjack.domain

import blackjack.domain.state.Started
import blackjack.domain.state.State

class Dealer(state: State = Started()) : Participant(state) {
    override fun receiveCard(card: Card) {
        state = state.draw(card)
        if (state.scoring().value >= Score.DEALER_STAND_THRESHOLD) turnStand()
    }
}
