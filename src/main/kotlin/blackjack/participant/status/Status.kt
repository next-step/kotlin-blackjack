package blackjack.participant.status

import blackjack.participant.BettingAmount
import blackjack.participant.Result

interface Status {
    fun calculateBettingAmount(result: Result, bettingAmount: BettingAmount): BettingAmount
}