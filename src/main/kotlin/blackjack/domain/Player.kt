package blackjack.domain

import blackjack.domain.state.Hit
import blackjack.domain.state.Started
import blackjack.domain.state.State

class Player(name: String) : Participant(name) {
    override fun receiveCard(card: Card) {
        state = state.draw(card)
    }
}
