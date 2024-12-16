package blackjack.domain

import java.math.BigDecimal

@JvmInline
value class BetMoney(private val amount: BigDecimal) {
    fun getOriginalBetAmount(): BigDecimal {
        return amount
    }

    fun getAmountOnBlackJack(): BigDecimal {
        return amount.multiply((1.5).toBigDecimal())
    }

    fun getAmountOnBust(): BigDecimal {
        return -(amount)
    }

    fun getAmountOnLose(): BigDecimal {
        return -(amount)
    }
}
