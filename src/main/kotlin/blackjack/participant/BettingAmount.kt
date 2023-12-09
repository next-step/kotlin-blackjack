package blackjack.participant

import kotlin.math.roundToInt

@JvmInline
value class BettingAmount(
    val amount: Int
) {
//    fun changeNegative(): BettingAmount {
//        return BettingAmount(amount * -1)
//    }

    fun winToBlackjack(): BettingAmount {
        return BettingAmount((amount * 1.5).roundToInt())
    }

    operator fun unaryMinus(): BettingAmount {
        return BettingAmount(amount * -1)
    }
}

infix operator fun BettingAmount.plus(bettingAmount: BettingAmount): BettingAmount {
    return BettingAmount(amount + bettingAmount.amount)
}

infix operator fun BettingAmount.minus(bettingAmount: BettingAmount): BettingAmount {
    return BettingAmount(amount - bettingAmount.amount)
}
