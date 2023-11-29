package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Hand

data class DealerPlayer(
    override val hand: Hand = Hand(),
) : CardPlayer {
    override fun hitOrStand(): Action {
        if (this isGreaterCardScoreThan HIT_THRESHOLD_SCORE) return Action.STAND
        return Action.HIT
    }

    companion object {
        private const val HIT_THRESHOLD_SCORE = 16
    }
}
