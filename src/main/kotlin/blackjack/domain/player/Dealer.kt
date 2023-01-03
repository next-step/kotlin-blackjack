package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.state.State

class Dealer(state: State) : Player("딜러", state) {
    init {
        if (canDraw()) dealersHitToStay()
    }

    override fun draw(card: Card) {
        state = state.draw(card)

        if (canDraw()) dealersHitToStay()
    }

    private fun dealersHitToStay() {
        if (state.score() > DEALERS_HIT_RULE) {
            this.state = state.stay()
        }
    }

    companion object {
        const val DEALERS_HIT_RULE = 16
    }
}
