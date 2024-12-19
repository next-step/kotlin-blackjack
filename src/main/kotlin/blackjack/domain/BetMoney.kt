package blackjack.domain

import blackjack.domain.GameResult.*
import java.math.BigDecimal
import java.math.RoundingMode

@JvmInline
value class BetMoney(private val amount: BigDecimal) {
    fun getAmount(gameResult: GameResult): BigDecimal {
        return when (gameResult) {
            BLACK_JACK -> getAmountOnBlackJack()
            WIN -> getOriginalBetAmount()
            PUSH -> getOriginalBetAmount()
            LOSE -> getAmountOnLose()
            BUST -> getAmountOnBust()
        }
    }

    private fun getOriginalBetAmount(): BigDecimal {
        return amount
    }

    private fun getAmountOnBlackJack(): BigDecimal {
        return amount.multiply((1.5).toBigDecimal()).setScale(0, RoundingMode.DOWN)
    }

    private fun getAmountOnBust(): BigDecimal {
        return -(amount)
    }

    private fun getAmountOnLose(): BigDecimal {
        return -(amount)
    }

}
