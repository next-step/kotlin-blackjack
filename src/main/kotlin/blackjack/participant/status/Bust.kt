package blackjack.participant.status

import blackjack.participant.BettingAmount
import blackjack.participant.Result

class Bust : Status {
    override fun calculateBettingAmount(result: Result, bettingAmount: BettingAmount): BettingAmount {
        return -bettingAmount
    }
}
