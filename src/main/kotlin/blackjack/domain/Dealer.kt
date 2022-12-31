package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.state.Hit
import blackjack.domain.state.State

class Dealer(override var state: State) : Player("딜러", state) {
    override fun canDraw(): Boolean {
        return state is Hit && state.hand.score <= DEALERS_HIT_RULE
    }

    fun allOpen(): List<Card> {
        return state.hand.cards
    }

    companion object {
        const val DEALERS_HIT_RULE = 16
    }
}
