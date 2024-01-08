package blackjack.participant.status

import blackjack.participant.BettingAmount
import blackjack.participant.Result

class Blackjack : Status {
    override fun calculateBettingAmount(result: Result, bettingAmount: BettingAmount): BettingAmount {
        return when(result) {
            is Result.Win -> bettingAmount.winToBlackjack()
            is Result.Lose -> BettingAmount(0)
        }
    }
}
