package blackjack.domain.state.started.finished

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.state.State
import blackjack.domain.state.started.Started
import java.math.BigDecimal
import java.math.RoundingMode

abstract class Finished(
    cards: Cards
) : Started(cards) {
    override fun takeCard(card: Card): State {
        throw IllegalStateException("Finished에서 카드를 더 받을 수 없습니다.")
    }

    override fun stay(): State {
        throw IllegalStateException("Finished에서 stay를 호출할 수 없습니다.")
    }

    override fun profit(betAmount: Int, dealerState: State): BigDecimal {
        val earningRatio = findEarningRatio(dealerState)
        return BigDecimal(betAmount).multiply(earningRatio).setScale(0, RoundingMode.FLOOR)
    }

    protected abstract fun findEarningRatio(dealerState: State): BigDecimal

    companion object {
        val BLACKJACK_EARNING_RATIO = BigDecimal("1.5")
        val EARNING_RATIO = BigDecimal("1")
        val LOSING_RATIO = BigDecimal("-1")
        val NO_EARNING_RATIO = BigDecimal("0")
    }
}
