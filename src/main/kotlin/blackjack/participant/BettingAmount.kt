package blackjack.participant

import kotlin.math.roundToInt

@JvmInline
value class BettingAmount(
    val amount: Int
) {
    fun changeNegative(): BettingAmount {
        return BettingAmount(amount * -1)
    }

    fun winToBlackjack(): BettingAmount {
        return BettingAmount((amount * 1.5).roundToInt())
    }
}
