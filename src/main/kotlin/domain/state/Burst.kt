package domain.state

import domain.card.Cards
import domain.player.PlayerGameResult
import java.math.BigDecimal

class Burst(cards: Cards) : TerminationState(cards) {
    override fun getPlayerGameResult(dealerState: State): PlayerGameResult = PlayerGameResult.LOSE

    override fun getRevenueRate(dealerState: State): BigDecimal = BURST_REVENUE_RATE

    companion object {
        private val BURST_REVENUE_RATE = BigDecimal(-1)
    }
}
