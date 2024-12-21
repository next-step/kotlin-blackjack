package blackjack.domain

import java.math.BigDecimal

sealed class Playing : State {
    override val isDone: Boolean get() = false

    override fun profit(bet: Bet): BigDecimal = throw IllegalStateException("현재 상태에서는 수익을 계산할 수 없습니다.")
}
