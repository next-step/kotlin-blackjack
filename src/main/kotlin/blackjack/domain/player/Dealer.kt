package blackjack.domain.player

import blackjack.domain.card.PlayingCard
import blackjack.domain.state.Hands
import blackjack.domain.state.Ready
import blackjack.domain.state.State

class Dealer {
    private var state: State = Ready()

    val hands: Hands
        get() = state.hands

    fun drawCard(card: PlayingCard) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }

    fun isRunning(): Boolean = state.isRunning()

    fun needsMoreCard(): Boolean {
        return hands.score() <= DRAW_THRESHOLD
    }

    companion object {
        private const val DRAW_THRESHOLD = 16
    }
}
