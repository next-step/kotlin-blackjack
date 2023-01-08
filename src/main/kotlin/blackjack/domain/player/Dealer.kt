package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.state.State

data class Dealer(override val state: State) : Player(name = "딜러", state) {
    override fun draw(card: Card) = this.copy(state = state.draw(card))
    override fun stay() = this.copy(state = state.stay())
    fun isDealersHit() = state.score() <= DEALERS_HIT_RULE

    companion object {
        const val DEALERS_HIT_RULE = 16
    }
}
