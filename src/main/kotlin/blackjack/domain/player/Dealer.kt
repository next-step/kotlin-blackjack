package blackjack.domain.player

import blackjack.domain.card.PlayingCard
import blackjack.domain.state.Bust
import blackjack.domain.state.Ready
import blackjack.domain.state.Running
import blackjack.domain.state.State

class Dealer(
    private var state: State = Ready()
) {

    fun drawCard(card: PlayingCard) {
        state = state.draw(card)
    }

    fun cards() = state.hands.cards
    fun stay() {
        state = state.stay()
    }

    fun isRunning(): Boolean = state is Running

    fun isBust(): Boolean = state is Bust


    fun needsMoreCard(): Boolean {
        return state.hands.score() <= DRAW_THRESHOLD
    }

    fun score(): Int {
        return state.hands.score()
    }

    companion object {
        private const val DRAW_THRESHOLD = 16
    }
}
