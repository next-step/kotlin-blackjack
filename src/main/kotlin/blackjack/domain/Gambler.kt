package blackjack.domain

import java.math.BigDecimal

class Gambler(name: String) : Participant(name) {
    private lateinit var betAmount: BigDecimal

    override fun isDealer() = false

    override fun canNotReceiveCard(): Boolean {
        return score >= BlackjackRule.BLACKJACK_SCORE
    }

    fun placeBet(betAmount: Long) {
        require(betAmount > 0) { "배팅 금액은 0원을 초과해야 합니다. 현재 입력 = $betAmount" }
        this.betAmount = betAmount.toBigDecimal()
    }

    fun calculateProfit(profitRate: BigDecimal): BigDecimal {
        return betAmount.multiply(profitRate)
    }
}
