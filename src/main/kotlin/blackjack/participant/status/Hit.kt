package blackjack.participant.status

import blackjack.participant.BettingAmount
import blackjack.participant.Result

class Hit : Status {
    override fun calculateBettingAmount(result: Result, bettingAmount: BettingAmount): BettingAmount {
        return when (result) {
            is Result.Win -> bettingAmount
            is Result.Lose -> bettingAmount.changeNegative()
        }
    }
}
