package blackjack.domain.user

import blackjack.domain.card.Cards
import blackjack.domain.status.*

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

        val pointResult = cards.getOptimizedPoint()
        status = ConditionalEndStatus.values()
            .firstOrNull { it.isMatch(pointResult) }
            ?: PlayingStatus.READY
    }

    fun updateResultStatus(resultStatus: ResultStatus) {
        status = resultStatus
    }

    open fun getFinalResult(): FinalResult {
        val resultStatus = status as ResultStatus
        val winCount = if(resultStatus.isPlayerWin) 1 else 0
        val loseCount = if(resultStatus.isDealerWin) 1 else 0
        return FinalResult(winCount, loseCount)
    }

    data class FinalResult(val winCount: Int, val loseCount: Int)


    fun isDone(): Boolean = status is EndStatus
    fun isBurst(): Boolean = status == ConditionalEndStatus.BURST
    fun wantHit(): Boolean = status == PlayingStatus.HIT


}
