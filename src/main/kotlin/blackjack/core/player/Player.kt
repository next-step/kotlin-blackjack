package blackjack.core.player

import blackjack.core.amount.Amount
import blackjack.core.amount.BettingAmount
import blackjack.core.amount.ProfitAmount

open class Player(name: Name, private val bettingAmount: BettingAmount = BettingAmount(0)) : Participant(name) {
    var profitAmount = ProfitAmount()

    fun getBettingAmount(): Amount {
        return if (checkBlackJack()) {
            Amount((bettingAmount.amount * BLACKJACK_BONUS_WEIGHT).toInt())
        } else {
            bettingAmount
        }
    }

    companion object {
        private const val BLACKJACK_BONUS_WEIGHT = 1.5f
    }
}
