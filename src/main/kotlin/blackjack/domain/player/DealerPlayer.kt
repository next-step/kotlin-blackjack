package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Hand

data class DealerPlayer(
    override val hand: Hand = Hand(),
) : CardHolder {
    override fun hitOrStand(): Action {
        if (isGreaterCardScoreThan(HIT_THRESHOLD_SCORE)) return Action.STAND
        return Action.HIT
    }

    companion object {
        private const val HIT_THRESHOLD_SCORE = 16
    }
}
