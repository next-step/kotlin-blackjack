package blackjack.core.player

import blackjack.core.amount.BettingAmount
import blackjack.core.amount.ProfitAmount

open class Player(name: Name, val bettingAmount: BettingAmount = BettingAmount(0)) : Participant(name) {
    var profitAmount = ProfitAmount()
}
