package blackjack.domain.state

import blackjack.domain.card.PlayingCard
import blackjack.domain.player.DealerState

interface State {
    val hands: Hands

    fun draw(card: PlayingCard): State

    fun stay(): State

    fun profit(
        betAmount: Int,
        dealerState: DealerState,
    ): Double

    fun isRunning(): Boolean
}
