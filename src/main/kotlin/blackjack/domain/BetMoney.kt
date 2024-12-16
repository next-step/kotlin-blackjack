package blackjack.domain

import java.math.BigDecimal
import java.math.RoundingMode

@JvmInline
value class BetMoney(private val amount: BigDecimal) {
    fun getOriginalBetAmount(): BigDecimal {
        return amount
    }

    fun getAmountOnBlackJack(): BigDecimal {
        return amount.multiply((1.5).toBigDecimal()).setScale(0, RoundingMode.DOWN)
    }

    fun getAmountOnBust(): BigDecimal {
        return -(amount)
    }

    fun getAmountOnLose(): BigDecimal {
        return -(amount)
    }
}
