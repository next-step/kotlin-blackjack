package blackjack.domain.player

import blackjack.domain.card.PlayingCard
import blackjack.domain.state.Bust
import blackjack.domain.state.Ready
import blackjack.domain.state.State

abstract class Participant(
    protected var state: State = Ready(),
) {
    fun cards() = state.hands.cards

    fun drawCard(card: PlayingCard) {
        state = state.draw(card)
    }

    fun stay() {
        state = state.stay()
    }

    fun isRunning(): Boolean = state.isRunning()

    fun isBust(): Boolean = state is Bust

    fun score(): Int = state.hands.score()
}
