package blackjack.domain.user

import blackjack.domain.card.Cards
import blackjack.domain.status.ConditionalEndStatus
import blackjack.domain.status.EndStatus
import blackjack.domain.status.FixedEndStatus
import blackjack.domain.status.PlayingStatus
import blackjack.domain.status.Status

open class Player(val name: String, val cards: Cards = Cards()) {
    private var status: Status = PlayingStatus.READY

    fun chooseHitOrStay(isPlayerWantHit: Boolean) {
        if (isPlayerWantHit) {
            status = PlayingStatus.HIT
            return
        }
        status = FixedEndStatus.STAY
    }

    fun updateStatus() {
        if (isDone()) return

        val pointResult = cards.getMinAndMaxPoint()
        status = ConditionalEndStatus.values()
            .firstOrNull { it.isMatch(pointResult) }
            ?: PlayingStatus.READY
    }

    fun isDone(): Boolean = status is EndStatus
    fun wantHit(): Boolean = status == PlayingStatus.HIT
}
