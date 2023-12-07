package blackjack.participant

import kotlin.math.roundToInt

@JvmInline
value class BettingAmount(
    val amount: Int
) {
    init {
        require(amount >= 0) { VALID_MESSAGE }
    }

    fun changeNegative(): BettingAmount {
        return BettingAmount(amount * -1)
    }

    fun winToBlackjack(): BettingAmount {
        return BettingAmount((amount * 1.5).roundToInt())
    }

    companion object {
        private const val VALID_MESSAGE: String = "베팅 금액은 0 이하일 수 없습니다."
    }
}
