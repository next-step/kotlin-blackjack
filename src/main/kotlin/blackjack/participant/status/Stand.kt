package blackjack.participant.status

import blackjack.participant.BettingAmount
import blackjack.participant.Result

class Stand : Status {
    override fun calculateBettingAmount(result: Result, bettingAmount: BettingAmount): BettingAmount {
        return when(result) {
            is Result.Win -> bettingAmount
            is Result.Lose -> bettingAmount.changeNegative()
            else -> throw IllegalArgumentException(ERROR_MESSAGE)
        }
    }

    companion object {
        private const val ERROR_MESSAGE: String = "잘못된 값입니다."
    }
}
